package com.example.java6.repository;

import com.example.java6.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAccountByUsername(String username);

    Account findAccountByEmail(String email);

    List<Account> findAll();

    void deleteAccountByUsername(String username);
}
