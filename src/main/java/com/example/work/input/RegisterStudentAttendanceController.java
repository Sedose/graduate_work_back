package com.example.work.input;

import com.example.work.input.request.Attendances;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attendances> registerAttendanceUsingFile(@RequestBody Attendances attendances) {
        System.out.println(attendances);
        return ResponseEntity.ok(attendances);
    }
}
