package com.example.work.controller.advice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static com.example.work.exception.ErrorCode.*;

@Configuration
class Config {

    @Bean
    public ErrorCodeToResponseEntityMap errorCodeToResponseMap() {
        return new ErrorCodeToResponseEntityMap(new HashMap<>() {{
            put(ACCESS_TOKEN_INVALID, ResponseEntity.status(HttpStatus.FORBIDDEN).build());
            put(CANNOT_GET_USER_BY_FULL_NAME, ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            put(CANNOT_EXTRACT_PARTS_FROM_USER_FULL_NAME, ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        }});
    }
}
