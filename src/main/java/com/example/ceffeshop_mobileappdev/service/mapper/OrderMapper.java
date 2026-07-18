package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Order;
import com.example.ceffeshop_mobileappdev.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "branchID.id", target = "branchId")
    @Mapping(source = "customerID.id", target = "customerId")
    @Mapping(source = "customerID.userID.fullName", target = "customerName")
    @Mapping(source = "cashierID.id", target = "cashierId")
    @Mapping(source = "shiftID.id", target = "shiftId")
    @Mapping(source = "orderStatus", target = "status")
    OrderDTO toDto(Order entity);

    @Mapping(source = "branchId", target = "branchID.id")
    @Mapping(source = "customerId", target = "customerID.id")
    @Mapping(source = "cashierId", target = "cashierID.id")
    @Mapping(source = "shiftId", target = "shiftID.id")
    @Mapping(source = "status", target = "orderStatus")
    Order toEntity(OrderDTO dto);
}
