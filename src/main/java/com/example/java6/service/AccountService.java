package com.example.java6.service;

import com.example.java6.entity.Account;

import java.util.List;

public interface AccountService {
    Account findById(String username);
    List<Account> findAll();
    List<Account> getAdministrators();
    void save(Account account); // Thêm phương thức lưu người dùng mới
}
