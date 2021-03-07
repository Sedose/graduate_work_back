package com.example.work.input.request;

import lombok.Value;

import java.time.Instant;

@Value
public class Attendance {
    String fullName;
    String userAction;
    Instant timestamp;
}
