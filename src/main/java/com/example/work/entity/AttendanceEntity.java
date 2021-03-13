package com.example.work.entity;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Value
@Table("user_attendances")
public class AttendanceEntity {
    @Id
    Integer id;
    Integer userId;
    Integer courseId;
    Date registeredTimestamp;
}
