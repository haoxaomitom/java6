package com.example.java6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping({"/", "/home/index"})
    public String home() {
        System.out.println("Home Page");
        return "redirect:/product/list";
    }
    @RequestMapping({"/admin","/admin/home/index"})
    public String admin(){
        System.out.println("go to admin page");
        return "admin/index";
    }
}
