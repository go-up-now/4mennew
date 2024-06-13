package com.poly.mapper;

import com.poly.dto.request.RoleRequest;
import com.poly.dto.respone.RoleResponse;
import com.poly.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
