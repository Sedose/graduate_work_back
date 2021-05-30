package com.example.work.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("student_groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentGroupEntity {
    @Id
    Integer id;
    String name;
}
