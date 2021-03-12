package com.example.work.input

import com.example.work.input.response.UserDetailsResponse
import com.example.work.security.SecurityUser
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-details")
@RequiredArgsConstructor
class UserDetailsController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun retrieveUserDetailsByToken(authentication: Authentication): UserDetailsResponse {
        val securityUser = (authentication.principal as SecurityUser)
        return UserDetailsResponse(securityUser.userRole.name)
    }
}
