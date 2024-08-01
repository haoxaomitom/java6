package com.example.java6.service.impl;

import com.example.java6.entity.Role;
import com.example.java6.repository.RoleRepository;
import com.example.java6.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
