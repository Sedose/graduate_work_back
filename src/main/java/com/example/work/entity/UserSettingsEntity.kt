package com.example.work.entity;

import lombok.Value;

@Value
public class UserSettingsEntity {
    String code;
    String description;
    String value;
    String defaultValue;
}
