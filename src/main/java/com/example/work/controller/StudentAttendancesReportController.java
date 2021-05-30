package com.example.work.controller;

import com.example.work.response.body.StudentAttendancesReport;
import com.example.work.service.TrainingRepresentativeService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

пойми, почему не работает для для других групп, курсов
@Setter
@RestController
@RequestMapping("api/student-attendances-report")
class StudentAttendancesReportController {

    @Autowired @Qualifier("trainingRepresentativeService")
    private TrainingRepresentativeService trainingRepresentativeService;

    @GetMapping
    @PreAuthorize("hasAuthority('attendances-report:read')")
    StudentAttendancesReport getAttendancesReportController(
        @RequestParam Integer studentGroupId,
        @RequestParam Integer courseId
    ) {
        return trainingRepresentativeService.formStudentAttendancesReportByGroupIdAndCourseId(
            studentGroupId,
            courseId
        );
    }
}