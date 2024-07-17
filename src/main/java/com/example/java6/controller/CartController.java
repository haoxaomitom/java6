package com.example.java6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/cart")
public class CartController {

    @GetMapping("/view")
        public String index() {
    return "cart/view";
    }
}
