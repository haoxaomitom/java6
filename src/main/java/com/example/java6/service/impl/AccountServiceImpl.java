package com.example.java6.service.impl;

import com.example.java6.entity.Account;
import com.example.java6.repository.AccountRepository;
import com.example.java6.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(String username) {
        return accountRepository.findById(username).orElseThrow();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAdministrators() {
        return accountRepository.getAdministrators();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
