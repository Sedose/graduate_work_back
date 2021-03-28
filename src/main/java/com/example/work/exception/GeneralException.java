package com.example.work.exception;

import com.example.work.exception.general.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralException extends RuntimeException {
    ErrorCode code;

    public GeneralException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
}
