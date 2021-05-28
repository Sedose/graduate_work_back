package com.example.work.controller.response.body;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CoursesModel {
    private List<Course> courses;

    public CoursesModel(List<Course> courses) {
        this.courses = courses;
    }

    public CoursesModel() {
    }
}
