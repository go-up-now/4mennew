package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class StatisticalController {
    @GetMapping("/revenues")
    public String getRevenues(){
        return "admin/statistical/revenue";
    }

    @GetMapping("/quantities")
    public String getQuantities(){
        return "admin/statistical/quantity";
    }
}
