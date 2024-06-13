package com.poly.api;

import com.poly.dto.request.IntrospectRequest;
import com.poly.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AuthorizedControllerApi {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/homes")
    public ResponseEntity<?> getAdminHomePage() {
        Map<String, Object> result = new HashMap<>();

            result.put("success", true);
            result.put("url", "/4men/admin/home");

        return ResponseEntity.ok(result);
    }
}
