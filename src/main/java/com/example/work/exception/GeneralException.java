package com.example.work.exception;

import com.example.work.exception.general.ErrorCodes;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralException extends RuntimeException {
    ErrorCodes code;
}
