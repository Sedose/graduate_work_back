package com.example.work.controller.advice;

import com.example.work.exception.ErrorCode;
import com.example.work.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.example.work.exception.ErrorCode.ACCESS_TOKEN_INVALID;
import static com.example.work.exception.ErrorCode.CANNOT_GET_USER_BY_FULL_NAME;

@ControllerAdvice
@RequiredArgsConstructor
public class GeneralControllerAdvice {

    private final Map<ErrorCode, ResponseEntity<String>> map;

    @ExceptionHandler(value = { GeneralException.class })
    public ResponseEntity<String> handleException(GeneralException exception) {
        return map.get(exception.getCode());
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<String> handleException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}

@Configuration
class Config {

    @Bean
    public Map<ErrorCode, ResponseEntity<String>> errorCodeToResponseMap() {
        return new HashMap<>() {{
           put(ACCESS_TOKEN_INVALID, ResponseEntity.status(HttpStatus.FORBIDDEN).build());
           put(CANNOT_GET_USER_BY_FULL_NAME, ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }};
    }
}
