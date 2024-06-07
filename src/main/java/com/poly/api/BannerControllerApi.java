package com.poly.api;

import com.poly.dto.request.*;
import com.poly.entity.Galery;
import com.poly.entity.User;
import com.poly.service.BannerService;
import com.poly.service.BrandService;
import com.poly.utils.Ximages;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/banner")
public class BannerControllerApi {
    @Autowired
    BannerService bannerService;

    @GetMapping
    public ResponseEntity<?> getAllBrand(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get brands success");
            result.put("data", bannerService.getAllBanners());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Object> createBanner(
            @RequestPart("data") @Valid
            BannerCreateRequest request, @RequestPart( value = "img", required = false)
            MultipartFile img){
        Map<String, Object> result = new HashMap<>();
        try {
            if(img != null && !img.isEmpty()){
                String imagePath = Ximages.saveImage(img);
                request.setThumbnail(imagePath);
            }
            result.put("success", true);
            result.put("message", "Save user success");
            result.put("data", bannerService.createBanner(request));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "get user success");
            result.put("data", bannerService.getBannerById(id));

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBanner(@PathVariable Long id, @RequestPart("data") BannerUpdateRequest request, @RequestPart(value = "img", required = false) MultipartFile img){
        Map<String, Object> result = new HashMap<>();
        try {
            if(img != null && !img.isEmpty()){
                // Nếu chọn hình mới thì lưu lại
                String imagePath = Ximages.saveImage(img);
                request.setThumbnail(imagePath);
            }
            else {
                // Lấy hình cũ nếu không truyển hình mới
                Galery galery = bannerService.getBannerById(id);
                request.setThumbnail(galery.getThumbnail());
            }
            result.put("success", true);
            result.put("message", "Update user success");
            result.put("data", bannerService.updateBanner(request, id));
        }catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Delete user success");
            bannerService.deleteProduct(id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

}
