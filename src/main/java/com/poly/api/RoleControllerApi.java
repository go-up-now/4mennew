package com.poly.api;

import com.poly.entity.Role;
import com.poly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/roles")
public class RoleControllerApi {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "Get roles success");
        result.put("data", roleService.getAllRoles());

        return ResponseEntity.ok(result);
    }
}
