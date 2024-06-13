package com.poly.service.Impl;

import com.poly.dto.request.RoleRequest;
import com.poly.dto.respone.RoleResponse;
import com.poly.entity.Role;
import com.poly.mapper.RoleMapper;
import com.poly.repository.PermissionRepository;
import com.poly.repository.RoleRepository;
import com.poly.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll()
                .stream().map(roleMapper::toRoleResponse)
                .toList();
    }

    @Override
    public RoleResponse create(RoleRequest request) {
        Role role =roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public void delete(String name) {
        roleRepository.deleteById(name);
    }
}
