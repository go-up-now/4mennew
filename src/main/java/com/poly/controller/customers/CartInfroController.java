package com.poly.controller.customers;

import org.springframework.web.bind.annotation.RequestMapping;

public class CartInfroController {
    @RequestMapping("/gio-hang")
    public String getcartInfor(){
        return "customers/carts/cart";
    }
}
