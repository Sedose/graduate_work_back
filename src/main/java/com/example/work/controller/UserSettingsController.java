package com.example.work.controller;

import com.example.work.controller.response.body.UserSettingsResponseBody;
import com.example.work.security.SecurityUser;
import com.example.work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserSettingsController {

    private final UserService userService;

    @GetMapping("/user-settings")
    public UserSettingsResponseBody fetchUserSettings(Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        return userService.findSettingsByUserId(securityUser.getId());
    }
}
