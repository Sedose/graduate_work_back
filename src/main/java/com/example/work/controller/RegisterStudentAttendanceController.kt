package com.example.work.controller

import com.example.work.controller.request.body.AttendancesRequestBody
import com.example.work.security.SecurityUser
import com.example.work.service.LecturerService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/attendance-register-file")
class RegisterStudentAttendanceController (
    private val lecturerService: LecturerService,
) {


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('attendances:write')")
    fun registerAttendanceUsingFile(
        @RequestBody attendancesRequestBody: @Valid AttendancesRequestBody,
        authentication: Authentication,
    ): ResponseEntity<Void> {
        val securityUser = authentication.principal as SecurityUser
        lecturerService.registerAttendanceUsingFile(
            attendancesRequestBody,
            securityUser.id
        )
        return ResponseEntity.ok().build()
    }
}