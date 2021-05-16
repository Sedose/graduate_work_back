package com.example.work.entity;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Table("users_settings")
public class UserSettingEntity {

    @Id Integer id;
    Integer userId;
    Integer settingId;
    String value;
}
