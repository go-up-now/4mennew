package com.poly.api;

import com.poly.entity.Email;
import com.poly.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailControllerApi {
    EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody Email email) {
        Map<String, Object> result = new HashMap<>();
        try {
            emailService.sendEmail(email);
            result.put("success", true);
            result.put("messages", "Sent success");
//            result.put("data", emailService.sendEmail(email));
        }
        catch (Exception e) {
            result.put("success", true);
            result.put("messages", e.getMessage());
//            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
