package com.poly.controller.customers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @RequestMapping("/dang-nhap")
    public String getLogin(){
        return "customers/accounts/login";
    }

    @RequestMapping("/dang-ky")
    public String getRegister(){
        return "customers/accounts/register";
    }

    @RequestMapping("/quen-mat-khau")
    public String getforgotPassword(){
        return "customers/accounts/forgot-password";
    }
}
