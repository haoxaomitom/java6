package com.example.java6.service.impl;

import com.example.java6.entity.Product;
import com.example.java6.repository.ProductRepository;
import com.example.java6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repo;

    @Override
    public List<Product> findAll() {return repo.findAll();}

    @Override
    public Product findById(Integer id){
        return repo.findById(id).get();
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        return repo.findByCategoryId(cid);
    }
}
