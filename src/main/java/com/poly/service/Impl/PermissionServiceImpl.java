package com.poly.service.Impl;

import com.poly.dto.request.PermissionRequest;
import com.poly.dto.respone.PermissionResponse;
import com.poly.entity.Permission;
import com.poly.mapper.PermissionMapper;
import com.poly.repository.PermissionRepository;
import com.poly.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PermissionServiceImpl implements PermissionService {

    PermissionMapper permissionMapper;
    PermissionRepository permissionRepository;

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public void delete(String name) {
        permissionRepository.deleteById(name);
    }
}
