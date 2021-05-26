package com.example.work.controller.request.body;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AttendancesRequestBody {

    @NotNull
    List<Attendance> attendances;
    @Min(1)
    Integer courseId;
    @NotNull
    Date registeredTimestamp;
}
