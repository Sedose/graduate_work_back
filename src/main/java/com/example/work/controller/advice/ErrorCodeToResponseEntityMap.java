package com.example.work.controller.advice;

import com.example.work.exception.ErrorCode;
import lombok.Value;
import lombok.experimental.Delegate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Value
public class ErrorCodeToResponseEntityMap {
    @Delegate
    Map<ErrorCode, ResponseEntity<String>> map;
}
