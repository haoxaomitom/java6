package com.example.java6.service;

import com.example.java6.entity.Product;
import com.example.java6.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

public interface ProductService  {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);
}
