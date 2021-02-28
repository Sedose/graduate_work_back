package com.example.work.input;

import com.example.work.student.attendance.StudentAttendanceService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/attendance-register-file")
public class RegisterStudentAttendance {
    private final StudentAttendanceService studentAttendanceService;

    @PostMapping
    public void registerAttendanceUsingFile(
            @RequestParam MultipartFile file
    ) {
        System.out.println(file);
    }
}
