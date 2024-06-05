package com.poly.api;

import com.poly.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/categories")
public class CategoryControllerApi {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get categories success");
            result.put("data", categoryService.getAllCategories());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
