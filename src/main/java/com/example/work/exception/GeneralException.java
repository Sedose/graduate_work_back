package com.example.work.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralException extends RuntimeException {
    public ErrorCode code;

    public GeneralException(ErrorCode code) {
        this.code = code;
    }

    public GeneralException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
}
