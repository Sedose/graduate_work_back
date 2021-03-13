package com.example.work.controller.request.body;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AttendancesRequestBody {
    List<Attendance> attendances;
    Integer courseId;
    Date registeredTimestamp;
}
