package com.example.work.input;

import com.example.work.attendance.file.service.ExcelService;
import com.example.work.input.request.Attendance;
import com.example.work.student.attendance.StudentAttendanceService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/attendance-register-file")
public class RegisterStudentAttendanceController {
    private final StudentAttendanceService studentAttendanceService;
    private final ExcelService fileService;

    @PostMapping
    public void registerAttendanceUsingFile(@RequestBody List<Attendance> attendances) {
        System.out.println(attendances);
    }
}
