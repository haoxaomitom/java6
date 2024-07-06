package com.example.java6.service;

import com.example.java6.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<Category> findAll();
}
