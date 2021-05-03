package com.example.work.service;

import com.example.work.controller.request.body.Attendance;
import com.example.work.controller.request.body.AttendancesRequestBody;
import com.example.work.entity.AttendanceEntity;
import com.example.work.exception.GeneralException;
import com.example.work.repository.StudentAttendancesRepository;
import com.example.work.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.work.exception.ErrorCode.CANNOT_EXTRACT_PARTS_FROM_USER_FULL_NAME;
import static com.example.work.exception.ErrorCode.CANNOT_GET_USER_BY_FULL_NAME;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {

    StudentAttendancesRepository studentAttendancesRepository;
    UserRepository userRepository;

    public void registerAttendanceUsingFile(AttendancesRequestBody attendancesRequestBody) {
        attendancesRequestBody.getAttendances()
                .removeIf(it -> StringUtils.isBlank(it.getFullName()));
        studentAttendancesRepository.saveAll(toAttendanceEntities(attendancesRequestBody));
    }

    private List<AttendanceEntity> toAttendanceEntities(AttendancesRequestBody attendancesRequestBody) {
        return attendancesRequestBody.getAttendances()
                .stream()
                .map(it -> toAttendanceEntity(it, attendancesRequestBody))
                .collect(Collectors.toList());
    }

    private AttendanceEntity toAttendanceEntity(Attendance attendance, AttendancesRequestBody attendancesRequestBody) throws GeneralException {
        return new AttendanceEntity(
                null,
                retrieveUserIdFromFullName(attendance.getFullName()),
                attendancesRequestBody.getCourseId(),
                attendancesRequestBody.getRegisteredTimestamp()
        );
    }

    private Integer retrieveUserIdFromFullName(String fullName) throws GeneralException {
        var user = retrieveUser(fullName);
        return userRepository.findByFirstNameAndMiddleNameAndLastName(
                user.firstName, user.middleName, user.lastName
        ).orElseThrow(() ->
                new GeneralException(CANNOT_GET_USER_BY_FULL_NAME)
        ).getId();
    }

    private User retrieveUser(String fullName) {
        String[] splitFullName = fullName.split(" ");
        if (splitFullName.length != 3) {
            throw new GeneralException(CANNOT_EXTRACT_PARTS_FROM_USER_FULL_NAME);
        }
        return new User(splitFullName[0], splitFullName[1], splitFullName[2]);
    }

    @ToString
    @AllArgsConstructor
    static class User {
        String firstName;
        String middleName;
        String lastName;
    }
}
