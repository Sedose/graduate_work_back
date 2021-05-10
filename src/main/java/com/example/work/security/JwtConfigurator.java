package com.example.work.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class JwtConfigurator extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Value("${jwt.header}")
    String authHeader;

    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        httpSecurity.addFilterBefore(
                new JwtTokenFilter(userDetailsService, authHeader),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
