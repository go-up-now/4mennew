package com.poly.service;


import com.poly.dto.request.PermissionRequest;
import com.poly.dto.respone.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAll();
    PermissionResponse create(PermissionRequest request);
    void delete(String name);
}
