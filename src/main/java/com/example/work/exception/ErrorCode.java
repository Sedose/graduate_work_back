package com.example.work.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CANNOT_GET_USER_BY_FULL_NAME(
            "cannot.get.user.by.full.name",
            "Cannot get user by full name"
    ),
    ACCESS_TOKEN_INVALID(
            "access.token.invalid",
            "JWT token is expired or invalid"
    );

    String code;
    String description;
}
