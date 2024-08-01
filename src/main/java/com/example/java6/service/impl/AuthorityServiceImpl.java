package com.example.java6.service.impl;

import com.example.java6.entity.Account;
import com.example.java6.entity.Authority;
import com.example.java6.repository.AccountRepository;
import com.example.java6.repository.AuthorityRepository;
import com.example.java6.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Authority create(Authority auth) {
        return authorityRepository.save(auth);
    }

    public void delete(Integer id) {
        authorityRepository.deleteById(id);
    }
    public List<Authority> findAuthoritiesOfAdministrators() {
        List<Account> accounts = accountRepository.getAdministrators();
        return authorityRepository.authoritiesOf(accounts);
    }
}
