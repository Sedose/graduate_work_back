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
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.work.exception.ErrorCode.CANNOT_GET_USER_BY_FULL_NAME;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {

    StudentAttendancesRepository studentAttendancesRepository;
    UserRepository userRepository;

    public void registerAttendanceUsingFile(AttendancesRequestBody attendancesRequestBody) {
        List<AttendanceEntity> attendanceEntities = attendancesRequestBody.getAttendances()
                .stream()
                .map(it -> toAttendanceEntity(it, attendancesRequestBody))
                .collect(Collectors.toList());
        studentAttendancesRepository.saveAll(attendanceEntities);
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
        String[] splitFullName = fullName.split(" ");
        var user = new User(splitFullName[0], splitFullName[1], splitFullName[2]);
        return userRepository.findByFirstNameAndMiddleNameAndLastName(
                user.firstName, user.middleName, user.lastName
        ).orElseThrow(() ->
                new GeneralException("Cannot find " + user.toString(), CANNOT_GET_USER_BY_FULL_NAME)
        ).getId();
    }
}

@AllArgsConstructor
class User {
    String firstName;
    String middleName;
    String lastName;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
