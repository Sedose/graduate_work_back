package com.example.work.lecturer.service;

import com.example.work.lecturer.mapper.CoursesMapper;
import com.example.work.lecturer.model.CoursesModel;
import com.example.work.lecturer.repo.CoursesRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerService {

    CoursesMapper coursesMapper;
    CoursesRepo coursesRepo;

    public CoursesModel retrieveAllCoursesByLecturerId(Long id) {
        return new CoursesModel(coursesMapper.map(coursesRepo.findAllByLecturerId(id)));
    }
}
