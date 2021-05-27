package com.example.work.service;

import com.example.work.controller.request.body.UserSettingsRequestBody;
import com.example.work.controller.response.body.UserSettingsResponseBody;
import com.example.work.mapper.CommonMapper;
import com.example.work.repository.UserSettingsRepository;
import com.example.work.security.SecurityUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private CommonMapper commonMapper;
    private UserSettingsRepository userSettingsRepository;

    public UserService() {
    }

    public UserService(CommonMapper commonMapper, UserSettingsRepository userSettingsRepository) {
        this.commonMapper = commonMapper;
        this.userSettingsRepository = userSettingsRepository;
    }

    public UserSettingsResponseBody findSettingsByUserId(Integer userId) {
        return new UserSettingsResponseBody(
                commonMapper.mapToUserSettingResponseBodyPartList(
                        userSettingsRepository.findUserSettingsByUserId(userId)
                )
        );
    }

    public void updateUserSettings(
            UserSettingsRequestBody userSettingsRequestBody,
            SecurityUser securityUser
    ) {
        for (var userSetting : userSettingsRequestBody.getUserSettings()) {
            userSettingsRepository.updateUserSettingsByUserId(
                    userSetting.getCode(),
                    userSetting.getNewValue(),
                    securityUser.getId()
            );
        }
    }
}
