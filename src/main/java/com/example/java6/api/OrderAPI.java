package com.example.java6.api;

import com.example.java6.entity.Order;
import com.example.java6.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    OrderService orderService;

    @PostMapping
    public Order create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);

    }
}
