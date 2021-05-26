package com.example.work.service;

import com.example.work.controller.request.body.Attendance;
import com.example.work.controller.request.body.AttendancesRequestBody;
import com.example.work.entity.AttendanceEntity;
import com.example.work.entity.UserSettingCode;
import com.example.work.exception.GeneralException;
import com.example.work.repository.StudentAttendancesRepository;
import com.example.work.repository.UserRepository;
import com.example.work.repository.UserSettingsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.work.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {

    private final StudentAttendancesRepository studentAttendancesRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final UserRepository userRepository;

    public void registerAttendanceUsingFile(
            AttendancesRequestBody attendancesRequestBody,
            Integer lecturerIdRegisteredBy
    ) {
        var now = attendancesRequestBody.getRegisteredTimestamp().toInstant();
        var was = studentAttendancesRepository.findMaxByRegisteredTimestampAndRegisteredBy(lecturerIdRegisteredBy);
        var userSettingMinFileUploadPeriodValueInSeconds = Duration.ofSeconds(Integer.parseInt(
                userSettingsRepository.findByCode(
                        UserSettingCode.MIN_STUDENT_ATTENDANCE_FILE_UPLOAD_INTERVAL.name()
                ).getValue()
        ));
        if(was != null && Duration.between(was, now).compareTo(userSettingMinFileUploadPeriodValueInSeconds) < 0) {
            throw new GeneralException(TOO_FREQUENT_FILE_UPLOADS);
        }
        attendancesRequestBody.getAttendances()
                .removeIf(it -> StringUtils.isBlank(it.getFullName()));
        studentAttendancesRepository.saveAll(toAttendanceEntities(
                attendancesRequestBody,
                lecturerIdRegisteredBy
        ));
    }

    private List<AttendanceEntity> toAttendanceEntities(
            AttendancesRequestBody attendancesRequestBody,
            Integer lecturerId
    ) {
        return attendancesRequestBody.getAttendances()
                .stream()
                .map(it -> toAttendanceEntity(
                        it,
                        attendancesRequestBody,
                        lecturerId
                )).collect(Collectors.toList());
    }

    private AttendanceEntity toAttendanceEntity(
            Attendance attendance,
            AttendancesRequestBody attendancesRequestBody,
            Integer lecturerId
    ) throws GeneralException {
        return new AttendanceEntity(
                null,
                retrieveUserIdFromFullName(attendance.getFullName()),
                attendancesRequestBody.getCourseId(),
                lecturerId,
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
