package com.poly.controller.customers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartInfroController {
    @RequestMapping("/gio-hang")
    public String getcartInfor(){
        return "customers/carts/cart";
    }
}
