package com.poly.service;

import com.poly.dto.request.RoleRequest;
import com.poly.dto.respone.RoleResponse;
import com.poly.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse create(RoleRequest request);
    void delete(String name);
}
