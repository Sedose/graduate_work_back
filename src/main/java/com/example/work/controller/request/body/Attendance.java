package com.example.work.controller.request.body;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Attendance {

    @NotNull
    String fullName;
    @NotNull
    String userAction;
    @NotNull
    String timestamp;
}
