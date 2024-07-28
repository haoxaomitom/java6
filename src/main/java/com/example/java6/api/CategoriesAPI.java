package com.example.java6.api;

import com.example.java6.entity.Category;
import com.example.java6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categories")
public class CategoriesAPI {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll()
    {
        return categoryService.findAll();
    }

}
