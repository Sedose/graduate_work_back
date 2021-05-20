package com.example.work.controller;

import com.example.work.controller.request.body.UserSettingsRequestBody;
import com.example.work.controller.response.body.UserSettingsResponseBody;
import com.example.work.security.SecurityUser;
import com.example.work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-settings")
public class UserSettingsController {

    private final UserService userService;


    @GetMapping
    public UserSettingsResponseBody fetchUserSettings(Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        return userService.findSettingsByUserId(securityUser.getId());
    }

    @PutMapping
    public void saveAllChanges(
            @RequestBody UserSettingsRequestBody userSettingsRequestBody,
            Authentication authentication
    ) {
        var securityUser = (SecurityUser)authentication.getPrincipal();
        userService.updateUserSettings(
                userSettingsRequestBody,
                securityUser
        );
    }
}
