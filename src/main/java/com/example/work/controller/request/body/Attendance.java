package com.example.work.controller.request.body;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Attendance {
    String fullName;
    String userAction;
    String timestamp;
}
