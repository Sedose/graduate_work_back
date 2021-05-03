package com.example.work.service;

import com.example.work.controller.response.body.CoursesModel;
import com.example.work.mapper.MainMapper;
import com.example.work.repository.CoursesRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerService {

    CoursesRepo coursesRepo;
    MainMapper mainMapper;

    public CoursesModel retrieveAllCoursesByLecturerId(Integer id) {
        return new CoursesModel(mainMapper.map(coursesRepo.findAllByLecturerId(id)));
    }
}
