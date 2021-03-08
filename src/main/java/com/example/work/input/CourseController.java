package com.example.work.input;

import com.example.work.lecturer.model.CoursesModel;
import com.example.work.lecturer.service.LecturerService;
import com.example.work.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final LecturerService lecturerService;

    @GetMapping
    @PreAuthorize("hasAuthority('courses:read')")
    ResponseEntity<CoursesModel> fetchAllCourses(Authentication authentication) {
        val securityUser = (SecurityUser)authentication.getPrincipal();
        return ResponseEntity.ok(lecturerService.retrieveAllCoursesByLecturerId(securityUser.getId()));
    }
}
