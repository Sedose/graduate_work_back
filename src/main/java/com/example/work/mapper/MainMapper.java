package com.example.work.mapper;

import com.example.work.controller.response.body.Course;
import com.example.work.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MainMapper {

    List<Course> map(List<CourseEntity> courseEntities);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    Course map(CourseEntity entity);
}
