package com.example.java6.service.impl;

import com.example.java6.entity.Account;
import com.example.java6.repository.AccountRepository;
import com.example.java6.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findById(String username) {
        return accountRepository.findById(username).get();
    }
}
