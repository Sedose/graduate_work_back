package com.example.work.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table("courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity {
    @Id
    Integer id;
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer lecturerId;
}
