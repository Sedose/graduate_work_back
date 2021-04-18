package com.example.work.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ACCESS_TOKEN_INVALID,
    CANNOT_GET_USER_BY_FULL_NAME,
    CANNOT_EXTRACT_PARTS_FROM_USER_FULL_NAME,
}
