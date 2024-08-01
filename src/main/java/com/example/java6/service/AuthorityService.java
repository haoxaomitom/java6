package com.example.java6.service;

import com.example.java6.entity.Authority;

import java.util.List;

public interface AuthorityService {
    public List<Authority> findAll() ;

    public Authority create(Authority auth) ;

    public void delete(Integer id) ;

    public List<Authority> findAuthoritiesOfAdministrators() ;

}
