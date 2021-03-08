package com.example.work.lecturer.repo;

import com.example.work.lecturer.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepo extends CrudRepository<CourseEntity, Integer> {

    List<CourseEntity> findAllByLecturerId(Long id);
}
