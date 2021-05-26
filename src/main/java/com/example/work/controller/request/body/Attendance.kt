package com.example.work.controller.request.body;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@FieldDefaults(level= PRIVATE)
public class Attendance {

    @NotNull
    String fullName;
    @NotNull
    String userAction;
    @NotNull
    String timestamp;
}
