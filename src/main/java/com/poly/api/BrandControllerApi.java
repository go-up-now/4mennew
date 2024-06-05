package com.poly.api;

import com.poly.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/brands")
public class BrandControllerApi {
    @Autowired
    BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAllBrand(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get brands success");
            result.put("data", brandService.getAllBrand());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
