package com.example.java6.service.impl;

import com.example.java6.entity.Order;
import com.example.java6.entity.OrderDetail;
import com.example.java6.repository.OrderDetailRepository;
import com.example.java6.repository.OrderRepository;
import com.example.java6.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData, Order.class);
        orderRepository.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>(){};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailRepository.saveAll(details);

        return order;
    }

    @Override
    public Order findById(Long id){
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username){
        return orderRepository.findByUsername(username);
    }
}
