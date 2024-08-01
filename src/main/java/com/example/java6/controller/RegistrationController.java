package com.example.java6.controller;

import com.example.java6.entity.Account;
import com.example.java6.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/security/register/form")
    public String registrationForm() {
        return "security/register";
    }

    @PostMapping("/security/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String fullname,
                               @RequestParam String email) {
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        newAccount.setFullname(fullname);
        newAccount.setEmail(email);

        // Lưu vào cơ sở dữ liệu
        accountService.save(newAccount);

        return "redirect:/security/login/form"; // Chuyển hướng đến trang đăng nhập sau khi đăng ký
    }
}
