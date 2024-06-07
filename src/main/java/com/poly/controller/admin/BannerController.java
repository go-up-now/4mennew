package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BannerController {
        @GetMapping("/banner")
        public String getBanner(){
            return "admin/banner/index";
        }

        @GetMapping("/banner/{id}")
        public String getBannerId(){
            return "admin/banner/index";
        }

    }

