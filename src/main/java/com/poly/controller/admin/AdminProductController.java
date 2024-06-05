package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminProductController {
    @GetMapping("/products")
    public String index(){
        return "admin/products/index";
    }

    @GetMapping({"/add-product", "/update-product/{id}"})
    public String add(){
        return "admin/products/add";
    }
}
