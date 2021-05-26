package com.example.work.controller.advice;

import com.example.work.exception.GeneralException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GeneralControllerAdvice {

    ErrorCodeToResponseEntityMap map;

    @ExceptionHandler(value = { GeneralException.class })
    public ResponseEntity<String> handleException(GeneralException exception) {
        return map.get(exception.getCode());
    }
}
