package com.example.java6.api;

import com.example.java6.entity.Product;
import com.example.java6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductAPI {
    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }
}
