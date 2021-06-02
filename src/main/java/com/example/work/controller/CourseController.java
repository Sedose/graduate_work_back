package com.example.work.controller;

import com.example.work.response.body.Course;
import com.example.work.security.SecurityUser;
import com.example.work.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final LecturerService lecturerService;

    @GetMapping
    @PreAuthorize("hasAuthority('courses:read')")
    ResponseEntity<Iterable<Course>> getAllCourses(Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        return ResponseEntity.ok(lecturerService.retrieveAllCoursesByLecturerId(securityUser.getId()));
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAuthority('courses:read')")
    ResponseEntity<Course> getCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok(lecturerService.findCourseById(courseId));
    }
}
