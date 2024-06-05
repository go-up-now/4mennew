package com.poly.controller.customers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/trang-chu")
    public String getIndex(){
        return "customers/index";
    }
}
