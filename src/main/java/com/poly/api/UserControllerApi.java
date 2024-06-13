package com.poly.api;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.entity.Email;
import com.poly.entity.User;
import com.poly.service.UserService;
import com.poly.utils.Ximages;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/users")
@Slf4j
public class UserControllerApi {

//    @Value("${upload.path}")
//    private String uploadPath;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestPart("data") @Valid UserCreationRequest request, @RequestPart( value = "img", required = false) MultipartFile img){
        Map<String, Object> result = new HashMap<>();
        try {
            if(img != null && !img.isEmpty()){
                String imagePath = Ximages.saveImage(img);
                request.setImg(imagePath);
            }
            result.put("success", true);
            result.put("message", "Save user success");
            result.put("data", userService.createUser(request));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestPart("data") UserUpdationRequest request, @RequestPart(value = "img", required = false) MultipartFile img){
        Map<String, Object> result = new HashMap<>();
        try {
            if(img != null && !img.isEmpty()){
                // Nếu chọn hình mới thì lưu lại
                String imagePath = Ximages.saveImage(img);
                request.setImg(imagePath);
            }
            else {
                // Lấy hình cũ nếu không truyển hình mới
                User user = userService.getUser(id);
                request.setImg(user.getImg());
            }
            result.put("success", true);
            result.put("message", "Update user success");
            result.put("data", userService.updateUser(request, id));
        }catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/change-password/{username}/{password}")
    public ResponseEntity<Object> updateUserPassword(@PathVariable String username, @PathVariable String password){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Update user success");
            userService.updateUserPassword(username, password);
        }catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Delete user success");
            userService.deleteUser(id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Object> getUsers(){

        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "get users success");
            result.put("data", userService.getAllUsers());

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "get user success");
            result.put("data", userService.getUser(id));

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check/username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "get username success");
//            result.put("data", userService.checkUsername(username));
            result.put("data", userService.getUserByUsername(username));
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/find-email")
    public ResponseEntity<?> findEmail(@RequestBody String email) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "get user success");
            result.put("data", userService.getUserByEmail(email));
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}