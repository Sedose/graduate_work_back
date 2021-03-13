package com.example.work.exception.general;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    CANNOT_GET_USER_BY_FULL_NAME("cannot.get.user.by.full.name");
    String code;

}
