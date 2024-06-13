package com.poly.mapper;

import com.poly.dto.request.PermissionRequest;
import com.poly.dto.respone.PermissionResponse;
import com.poly.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
