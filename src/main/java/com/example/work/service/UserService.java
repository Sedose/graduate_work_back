package com.example.work.service;

import com.example.work.controller.response.body.UserSettingsResponseBody;
import com.example.work.mapper.CommonMapper;
import com.example.work.repository.UserSettingsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    CommonMapper commonMapper;
    UserSettingsRepository userSettingsRepository;

    public UserSettingsResponseBody findSettingsByUserId(Integer userId) {
        return new UserSettingsResponseBody(
                commonMapper.mapToUserSettingResponseBodyPartList(
                        userSettingsRepository.findUserSettingsByUserId(userId)
                )
        );
    }
}
