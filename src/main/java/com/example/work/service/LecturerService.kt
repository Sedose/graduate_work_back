package com.example.work.service;

import com.example.work.controller.response.body.CoursesModel;
import com.example.work.mapper.CommonMapper;
import com.example.work.repository.CoursesRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerService {

    private final CoursesRepo coursesRepo;
    private final CommonMapper commonMapper;

    public CoursesModel retrieveAllCoursesByLecturerId(Integer id) {
        return new CoursesModel(commonMapper.map(coursesRepo.findAllByLecturerId(id)));
    }
}
