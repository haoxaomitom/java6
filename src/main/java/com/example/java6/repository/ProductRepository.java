package com.example.java6.repository;

import com.example.java6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    void deleteById(int id);
    Product findById(int id);

    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(String cid);
}
