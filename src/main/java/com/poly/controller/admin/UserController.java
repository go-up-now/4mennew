package com.poly.controller.admin;

import com.poly.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {

    @GetMapping("/users")
    public String index(){
        return "admin/users/index";
    }

    @GetMapping({"/add-user", "/update-user/{id}"})
    public String createUser(){
        return "admin/users/add";
    }

//    @PostMapping("/add-user/{id}")
//    public String saveUser(@ModelAttribute("user")User user){
//
//        return "redirect:/users";
//    }
}
