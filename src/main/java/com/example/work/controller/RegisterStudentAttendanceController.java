package com.example.work.controller;

import com.example.work.controller.request.body.AttendancesRequestBody;
import com.example.work.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/attendance-register-file")
public class RegisterStudentAttendanceController {

    private final StudentService studentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerAttendanceUsingFile(
            @RequestBody AttendancesRequestBody attendancesRequestBody
    ) {
        log.info(attendancesRequestBody.toString());
        studentService.registerAttendanceUsingFile(attendancesRequestBody);
        return ResponseEntity.ok().build();
    }
}
