package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.entity.*;
import com.example.ceffeshop_mobileappdev.repository.*;
import com.example.ceffeshop_mobileappdev.security.SecurityUtils;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.OrderDTO;
import com.example.ceffeshop_mobileappdev.service.dto.OrderStatusUpdateDTO;
import com.example.ceffeshop_mobileappdev.service.dto.request.PlaceOrderRequest;
import com.example.ceffeshop_mobileappdev.service.dto.response.PlaceOrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/mobile/orders")
@RequiredArgsConstructor
public class MobileOrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemCustomizationRepository orderItemCustomizationRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final MenuItemRepository menuItemRepository;
    private final CustomizationOptionRepository customizationOptionRepository;
    private final BranchRepository branchRepository;
    private final LoyaltyTransactionRepository loyaltyTransactionRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/place")
    public ResponseEntity<ApiResponse<PlaceOrderResponse>> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new RuntimeException("Chưa đăng nhập"));
        Customer customer = customerRepository.findByUserID_Id(userId.intValue())
            .orElseGet(() -> customerRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu khách hàng mặc định")));
        Branch branch = branchRepository.findById(request.getBranchId())
            .orElseThrow(() -> new RuntimeException("Chi nhánh không tồn tại"));

        BigDecimal subTotal = BigDecimal.ZERO;
        List<Map<String, Object>> itemCalculations = new ArrayList<>();

        for (PlaceOrderRequest.OrderItemRequest itemReq : request.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemReq.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món ăn với ID: " + itemReq.getMenuItemId()));

            BigDecimal unitPrice = menuItem.getBasePrice();

            if (itemReq.getCustomizationOptionIds() != null) {
                for (Integer optionId : itemReq.getCustomizationOptionIds()) {
                    CustomizationOption option = customizationOptionRepository.findById(optionId)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy tùy chọn: " + optionId));
                    unitPrice = unitPrice.add(option.getExtraPrice());
                }
            }

            BigDecimal itemSubTotal = unitPrice.multiply(BigDecimal.valueOf(itemReq.getQuantity()));
            subTotal = subTotal.add(itemSubTotal);

            Map<String, Object> calc = new HashMap<>();
            calc.put("menuItem", menuItem);
            calc.put("unitPrice", unitPrice);
            calc.put("quantity", itemReq.getQuantity());
            calc.put("subTotal", itemSubTotal);
            calc.put("notes", itemReq.getNotes());
            calc.put("optionIds", itemReq.getCustomizationOptionIds());
            itemCalculations.add(calc);
        }

        BigDecimal vatAmount = subTotal.multiply(new BigDecimal("0.10")).setScale(0, RoundingMode.HALF_UP);
        BigDecimal totalAmount = subTotal.add(vatAmount);

        BigDecimal discountAmount = BigDecimal.ZERO;
        Coupon appliedCoupon = null;

        if (request.getCouponCode() != null && !request.getCouponCode().isBlank()) {
            Optional<Coupon> couponOpt = couponRepository.findByCodeAndIsActiveTrue(request.getCouponCode());
            if (couponOpt.isPresent()) {
                Coupon coupon = couponOpt.get();
                Instant now = Instant.now();
                if (!now.isBefore(coupon.getStartDate()) && !now.isAfter(coupon.getEndDate())
                        && coupon.getUsedCount() < coupon.getUsageLimit()
                        && totalAmount.compareTo(coupon.getMinOrderValue()) >= 0) {

                    if ("Percentage".equalsIgnoreCase(coupon.getDiscountType())) {
                        discountAmount = totalAmount.multiply(coupon.getDiscountValue())
                            .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
                        if (coupon.getMaxDiscountAmount() != null) {
                            discountAmount = discountAmount.min(coupon.getMaxDiscountAmount());
                        }
                    } else {
                        discountAmount = coupon.getDiscountValue();
                    }
                    appliedCoupon = coupon;
                }
            }
        }

        BigDecimal pointsDiscount = BigDecimal.ZERO;
        if (request.getRedeemPoints() != null && request.getRedeemPoints() > 0) {
            if (customer.getLoyaltyPoints() < request.getRedeemPoints()) {
                throw new RuntimeException("Bạn không đủ điểm tích lũy để đổi");
            }
            pointsDiscount = BigDecimal.valueOf(request.getRedeemPoints()).multiply(BigDecimal.valueOf(100)); // 1 điểm = 100đ
        }

        BigDecimal finalAmount = totalAmount.subtract(discountAmount).subtract(pointsDiscount).max(BigDecimal.ZERO);

        Order order = new Order();
        order.setCustomerID(customer);
        order.setBranchID(branch);
        order.setOrderSource("MobileApp");
        order.setFulfillmentMode(request.getFulfillmentMode());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setTotalAmount(totalAmount);
        order.setVATAmount(vatAmount);
        order.setDiscountAmount(discountAmount.add(pointsDiscount));
        order.setFinalAmount(finalAmount);
        order.setOrderStatus("Pending");
        order.setPaymentStatus("Unpaid");
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setOrderCode("TEMP");
        Order savedOrder = orderRepository.save(order);

        String orderCode = "CF-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
            + "-" + String.format("%04d", savedOrder.getId());
        savedOrder.setOrderCode(orderCode);
        orderRepository.save(savedOrder);

        for (Map<String, Object> calc : itemCalculations) {
            MenuItem menuItem = (MenuItem) calc.get("menuItem");
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderID(savedOrder);
            orderItem.setQuantity((Integer) calc.get("quantity"));
            orderItem.setUnitPrice((BigDecimal) calc.get("unitPrice"));
            orderItem.setSubTotal((BigDecimal) calc.get("subTotal"));
            orderItem.setNotes((String) calc.get("notes"));
            OrderItem savedItem = orderItemRepository.save(orderItem);

            @SuppressWarnings("unchecked")
            List<Integer> optionIds = (List<Integer>) calc.get("optionIds");
            if (optionIds != null) {
                for (Integer optionId : optionIds) {
                    CustomizationOption option = customizationOptionRepository.findById(optionId).orElseThrow();
                    OrderItemCustomization oic = new OrderItemCustomization();
                    OrderItemCustomizationId oicId = new OrderItemCustomizationId();
                    oicId.setOrderItemID(savedItem.getId());
                    oicId.setOptionID(option.getId());
                    oic.setId(oicId);
                    oic.setOptionPrice(option.getExtraPrice());
                    orderItemCustomizationRepository.save(oic);
                }
            }
        }

        if (appliedCoupon != null) {
            appliedCoupon.setUsedCount(appliedCoupon.getUsedCount() + 1);
            couponRepository.save(appliedCoupon);
        }

        // Handle Points Redemption
        if (request.getRedeemPoints() != null && request.getRedeemPoints() > 0) {
            customer.setLoyaltyPoints(customer.getLoyaltyPoints() - request.getRedeemPoints());
            
            LoyaltyTransaction ltRedeem = new LoyaltyTransaction();
            ltRedeem.setCustomerID(customer);
            ltRedeem.setOrderID(savedOrder);
            ltRedeem.setPointsEarned(0);
            ltRedeem.setPointsRedeemed(request.getRedeemPoints());
            ltRedeem.setTransactionType("Redeem");
            ltRedeem.setCreatedAt(Instant.now());
            loyaltyTransactionRepository.save(ltRedeem);
        }

        // Handle Points Earning
        int pointsEarned = finalAmount.divide(BigDecimal.valueOf(10000), 0, RoundingMode.FLOOR).intValue();
        if (pointsEarned > 0) {
            customer.setLoyaltyPoints(customer.getLoyaltyPoints() + pointsEarned);
            
            LoyaltyTransaction ltEarn = new LoyaltyTransaction();
            ltEarn.setCustomerID(customer);
            ltEarn.setOrderID(savedOrder);
            ltEarn.setPointsEarned(pointsEarned);
            ltEarn.setPointsRedeemed(0);
            ltEarn.setTransactionType("Earn");
            ltEarn.setCreatedAt(Instant.now());
            loyaltyTransactionRepository.save(ltEarn);
        }

        // Update Tier & Save Customer
        int pts = customer.getLoyaltyPoints();
        if (pts >= 2000) customer.setMembershipTier("Gold");
        else if (pts >= 500) customer.setMembershipTier("Silver");
        else customer.setMembershipTier("Bronze");
        customerRepository.save(customer);

        PlaceOrderResponse response = new PlaceOrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setOrderCode(orderCode);
        response.setOrderStatus("Pending");
        response.setPaymentStatus("Unpaid");
        response.setTotalAmount(totalAmount);
        response.setDiscountAmount(discountAmount);
        response.setFinalAmount(finalAmount);
        response.setPointsEarned(pointsEarned);
        response.setCreatedAt(savedOrder.getCreatedAt());

        OrderStatusUpdateDTO wsEvent = new OrderStatusUpdateDTO(
            savedOrder.getId(), orderCode, "Pending", "Đơn hàng mới đang chờ xác nhận", Instant.now());
        messagingTemplate.convertAndSend("/topic/branch/" + branch.getId() + "/new-order", wsEvent);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(response, "Đặt hàng thành công"));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getMyOrders() {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new RuntimeException("Chưa đăng nhập"));
        Customer customer = customerRepository.findByUserID_Id(userId.intValue())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        List<Order> orders = orderRepository.findByCustomerID_Id(
            customer.getId(), Sort.by(Sort.Direction.DESC, "createdAt"));

        List<OrderDTO> dtos = orders.stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setOrderCode(order.getOrderCode());
            dto.setBranchId(order.getBranchID().getId());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setDiscountAmount(order.getDiscountAmount());
            dto.setFinalAmount(order.getFinalAmount());
            dto.setStatus(order.getOrderStatus());
            dto.setPaymentStatus(order.getPaymentStatus());
            dto.setPaymentMethod(order.getPaymentMethod());
            dto.setCreatedAt(order.getCreatedAt());
            return dto;
        }).toList();

        return ResponseEntity.ok(ApiResponse.success(dtos, "Lấy lịch sử đơn hàng thành công"));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByBranch(@PathVariable Integer branchId) {
        List<Order> orders = orderRepository.findAll().stream()
            .filter(o -> o.getBranchID() != null && o.getBranchID().getId().equals(branchId))
            .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
            .toList();

        List<OrderDTO> dtos = orders.stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setOrderCode(order.getOrderCode());
            dto.setBranchId(order.getBranchID().getId());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setDiscountAmount(order.getDiscountAmount());
            dto.setFinalAmount(order.getFinalAmount());
            dto.setStatus(order.getOrderStatus());
            dto.setPaymentStatus(order.getPaymentStatus());
            dto.setPaymentMethod(order.getPaymentMethod());
            dto.setCreatedAt(order.getCreatedAt());
            return dto;
        }).toList();

        return ResponseEntity.ok(ApiResponse.success(dtos, "Lấy đơn hàng chi nhánh thành công"));
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<OrderStatusUpdateDTO>> getOrderStatus(@PathVariable Integer orderId) {
        return orderRepository.findById(orderId)
            .map(order -> {
                String msg = switch (order.getOrderStatus()) {
                    case "Pending" -> "Đơn hàng đang chờ xác nhận...";
                    case "Confirmed" -> "Đơn hàng đã được xác nhận!";
                    case "Preparing" -> "Barista đang pha chế...";
                    case "Ready" -> "Đơn hàng đã sẵn sàng, vui lòng nhận món!";
                    case "Completed" -> "Đơn hàng đã hoàn thành!";
                    default -> order.getOrderStatus();
                };
                OrderStatusUpdateDTO dto = new OrderStatusUpdateDTO(
                    order.getId(), order.getOrderCode(), order.getOrderStatus(), msg, order.getUpdatedAt());
                return ResponseEntity.ok(ApiResponse.success(dto, "Lấy trạng thái đơn hàng thành công"));
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy đơn hàng")));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<Void>> updateOrderStatus(
            @PathVariable Integer orderId,
            @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        return orderRepository.findById(orderId)
            .map(order -> {
                order.setOrderStatus(newStatus);
                order.setUpdatedAt(Instant.now());
                orderRepository.save(order);

                String msg = switch (newStatus) {
                    case "Confirmed" -> "Đơn hàng đã xác nhận!";
                    case "Preparing" -> "Barista đang pha chế...";
                    case "Ready" -> "Đồ uống đã sẵn sàng!";
                    case "Completed" -> "Cảm ơn bạn đã mua hàng!";
                    case "Cancelled" -> "Đơn hàng đã bị hủy.";
                    default -> newStatus;
                };
                OrderStatusUpdateDTO wsEvent = new OrderStatusUpdateDTO(
                    orderId, order.getOrderCode(), newStatus, msg, Instant.now());
                messagingTemplate.convertAndSend("/topic/order/" + orderId, wsEvent);

                return ResponseEntity.ok(ApiResponse.<Void>success(null, "Cập nhật trạng thái thành công"));
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy đơn hàng")));
    }
}
