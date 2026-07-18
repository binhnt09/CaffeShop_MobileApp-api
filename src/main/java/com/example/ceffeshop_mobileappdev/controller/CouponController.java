package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.entity.Coupon;
import com.example.ceffeshop_mobileappdev.repository.CouponRepository;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.response.CouponValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponRepository couponRepository;

    @PostMapping("/validate")
    public ResponseEntity<ApiResponse<CouponValidationResult>> validateCoupon(@RequestBody Map<String, Object> body) {
        String code = (String) body.get("code");
        if (code == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Mã coupon không được trống"));
        }
        BigDecimal orderTotal = new BigDecimal(body.get("orderTotal").toString());

        return couponRepository.findByCodeAndIsActiveTrue(code)
            .map(coupon -> {
                Instant now = Instant.now();
                if (now.isBefore(coupon.getStartDate())) {
                    return ResponseEntity.ok(ApiResponse.success(
                        new CouponValidationResult(false, BigDecimal.ZERO, code, "Mã giảm giá chưa có hiệu lực"), "Mã chưa bắt đầu"));
                }
                if (now.isAfter(coupon.getEndDate())) {
                    return ResponseEntity.ok(ApiResponse.success(
                        new CouponValidationResult(false, BigDecimal.ZERO, code, "Mã giảm giá đã hết hạn"), "Mã hết hạn"));
                }
                if (coupon.getUsedCount() >= coupon.getUsageLimit()) {
                    return ResponseEntity.ok(ApiResponse.success(
                        new CouponValidationResult(false, BigDecimal.ZERO, code, "Mã giảm giá đã hết lượt sử dụng"), "Mã hết lượt"));
                }
                if (orderTotal.compareTo(coupon.getMinOrderValue()) < 0) {
                    return ResponseEntity.ok(ApiResponse.success(
                        new CouponValidationResult(false, BigDecimal.ZERO, code,
                            "Đơn hàng tối thiểu phải đạt " + coupon.getMinOrderValue().toPlainString() + "đ"), "Chưa đạt giá trị đơn tối thiểu"));
                }

                BigDecimal discount;
                if ("Percentage".equalsIgnoreCase(coupon.getDiscountType())) {
                    discount = orderTotal.multiply(coupon.getDiscountValue()).divide(BigDecimal.valueOf(100));
                    if (coupon.getMaxDiscountAmount() != null) {
                        discount = discount.min(coupon.getMaxDiscountAmount());
                    }
                } else {
                    discount = coupon.getDiscountValue();
                }

                return ResponseEntity.ok(ApiResponse.success(
                    new CouponValidationResult(true, discount, code, "Áp dụng mã giảm giá thành công"), "Mã hợp lệ"));
            })
            .orElseGet(() -> ResponseEntity.ok(ApiResponse.success(
                new CouponValidationResult(false, BigDecimal.ZERO, code, "Mã giảm giá không tồn tại hoặc đã bị vô hiệu hóa"), "Mã không tồn tại")));
    }
}
