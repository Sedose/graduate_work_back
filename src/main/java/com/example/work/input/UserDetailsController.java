package com.example.work.input;

import com.example.work.security.SecurityUser;
import com.example.work.user_details.response.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user-details")
@RequiredArgsConstructor
public class UserDetailsController {

    @GetMapping
    public UserDetailsResponse retrieveUserDetailsByToken(Authentication authentication) {
        var securityUser = (SecurityUser) authentication;
        return new UserDetailsResponse(securityUser.getUserRole().name());
    }
}
