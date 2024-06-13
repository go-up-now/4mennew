package com.poly.api;

import com.poly.dto.request.ApiResponse;
import com.poly.dto.request.PermissionRequest;
import com.poly.dto.respone.PermissionResponse;
import com.poly.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionControllerApi {
    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .success(true)
                .code(1000)
                .data(permissionService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermissions(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .success(true)
                .code(1000)
                .data(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<String> delete(@PathVariable String name){
        permissionService.delete(name);
        return ApiResponse.<String>builder()
                .success(true)
                .code(1000)
                .data("permission deleted")
                .build();
    }
}
