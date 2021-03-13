package com.example.work.repository;

import com.example.work.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepo extends CrudRepository<CourseEntity, Integer> {

    List<CourseEntity> findAllByLecturerId(Integer id);
}
