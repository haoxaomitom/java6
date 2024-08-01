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
    @RequestMapping({"/admin/product/index"})
    public String adminProduct(){
        System.out.println("go to admin product");
        return "admin/product/index";
    }
    @RequestMapping({"/admin/product/form"})
    public String adminProductForm(){
        System.out.println("go to admin product");
        return "admin/product/form";
    }
    @RequestMapping({"/admin/product/table"})
    public String adminProductTable(){
        System.out.println("go to admin product");
        return "admin/product/table";
    }

    @RequestMapping({"admin/authority/index"})
    public String authority(){
        System.out.println("go to authority page");
        return "admin/authority/index";
    }
}
