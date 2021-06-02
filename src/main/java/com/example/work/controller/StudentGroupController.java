package com.example.work.controller;

import com.example.work.entity.StudentGroupEntity;
import com.example.work.service.TrainingRepresentativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student-groups")
public class StudentGroupController {

    private final TrainingRepresentativeService trainingRepresentativeService;

    @GetMapping
    @PreAuthorize("hasAuthority('student-groups:read')")
    Iterable<StudentGroupEntity> getStudentGroups() {
        return trainingRepresentativeService.findAllStudentGroups();
    }

    @GetMapping("{groupId}")
    @PreAuthorize("hasAuthority('student-groups:read')")
    StudentGroupEntity getStudentGroupById(
            @PathVariable Integer groupId
    ) {
        return trainingRepresentativeService.findStudentGroupById(groupId);
    }
}
