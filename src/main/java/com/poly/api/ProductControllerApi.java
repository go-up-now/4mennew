package com.poly.api;

import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/products")
@RequiredArgsConstructor
public class ProductControllerApi {
    private final  ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get products success");
            result.put("data", productService.getAllProducts());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get product success");
            result.put("data", productService.getProductById(id));
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequest request){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "create product success");
            result.put("data", productService.createProduct(request));
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable int id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "update product success");
            result.put("data", productService.updateProduct(request, id));
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> createProduct(@PathVariable int id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "delete product success");
            productService.deleteProduct(id);
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/quantity")
    public ResponseEntity<?> getProductsByQuantity(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get products by quantity success");
            result.put("data", productService.getProductsByQuantity());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
