package com.example.work.security;

import com.example.work.exception.SomeEntityNotFoundException;
import com.example.work.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public SecurityUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return SecurityUser.fromUser(
                userRepository.findByEmail(email)
                        .orElseThrow(SomeEntityNotFoundException::new)
        );
    }
}
