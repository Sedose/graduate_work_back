package com.example.work.lecturer.mapper;

import com.example.work.lecturer.entity.CourseEntity;
import com.example.work.lecturer.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursesMapper {

    List<Course> map(List<CourseEntity> courseEntities);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    Course map(CourseEntity entity);
}
