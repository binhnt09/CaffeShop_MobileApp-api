package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roleID.roleName", target = "roleName")
    @Mapping(source = "roleID.id", target = "roleId")
    UserDTO toDto(User user);
}
