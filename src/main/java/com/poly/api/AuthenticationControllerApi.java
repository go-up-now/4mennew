package com.poly.api;

import com.poly.dto.request.UserAuthenticateRequest;
import com.poly.dto.respone.UserAuthRespone;
import com.poly.service.AuthenticationService;
import com.poly.utils.Ximages;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationControllerApi {
    AuthenticationService authenticationService;

    @PostMapping
    ResponseEntity<?> authenticate(@RequestBody UserAuthenticateRequest request){
        Map<String, Object> result = new HashMap<>();
        var authenticate = authenticationService.authenticate(request);
        try {
            result.put("success", true);
            result.put("message", "username valid");
            result.put("data", authenticate);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", authenticate);
        }
        return ResponseEntity.ok(result);
    }
}
