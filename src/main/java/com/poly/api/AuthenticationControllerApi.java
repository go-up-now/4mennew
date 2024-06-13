package com.poly.api;

import com.nimbusds.jose.JOSEException;
import com.poly.dto.request.*;
import com.poly.dto.respone.AuthenticationResponse;
import com.poly.dto.respone.IntrospectResponse;
import com.poly.dto.respone.UserResponse;
import com.poly.service.AuthenticationService;
import com.poly.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationControllerApi {
    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authentication(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .success(true)
                .code(1000)
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<?> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .success(true)
                .code(1000)
                .data(result)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .success(true)
                .code(1000)
                .build();
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .code(1000)
                .data(userService.createUser(request))
                .build();
    }

    @GetMapping("/my-information")
    public ApiResponse<UserResponse> getMyInformation(){
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .code(1000)
                .data(userService.getMyInformation())
                .build();
    }

    @PutMapping("/update-my-information")
    public ApiResponse<?> updateUser(@RequestBody UserUpdationRequest request){
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .code(1000)
                .data(userService.updateMyInformation(request))
                .build();
    }

    @PostMapping("/protected/resource")
    public ResponseEntity<String> getProtectedResource(@RequestBody IntrospectRequest request) {
        // Kiểm tra xác thực token
        String token = request.getToken();
        if (authenticationService.introspect(request).isValid()) {
                // Trả về dữ liệu tài nguyên bảo vệ
                return ResponseEntity.ok("Protected resource data");
        } else {
            // Token không hợp lệ
            return ResponseEntity.status(9999).body("Invalid token");
        }
    }

    // Phương thức này xác định xem người dùng từ token có quyền admin không
//    public boolean isAdminUser(String token) {
//        JwtClaimsSet claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        String role = claims.get("role", String.class);
//        // Kiểm tra xem vai trò của người dùng có là "admin" không
//        return "admin".equals(role);
//    }
}
