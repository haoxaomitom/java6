package com.example.java6.security;

import com.example.java6.entity.Account;
import com.example.java6.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountService accountService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/order/**").authenticated()
                        .requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
                        .requestMatchers("/security/register/form").permitAll() // Cho phép truy cập công khai
                        .requestMatchers("/security/register").permitAll() // Cho phép truy cập công khai
                        .requestMatchers("/rest/authorities").hasRole("DIRE")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/security/login/form")
                        .loginProcessingUrl("/security/login")
                        .defaultSuccessUrl("/security/login/success", false)
                        .failureUrl("/security/login/error")
                )
                .rememberMe(remember -> remember
                        .tokenValiditySeconds(86400)
                )
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/security/unauthorized")
                )
                .logout(lg -> lg
                        .logoutUrl("/security/logoff")
                        .logoutSuccessUrl("/security/logoff/success")
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                Account user = accountService.findById(username);
                String[] roles = user.getAuthorities().stream()
                        .map(er -> er.getRole().getId())
                        .toArray(String[]::new);
                return User.withUsername(username)
                        .password(user.getPassword())
                        .roles(roles)
                        .build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + " not found!");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**");
    }
}

