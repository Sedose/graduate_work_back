package com.example.work.repository

import com.example.work.entity.UserSettingsEntity
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface UserSettingsRepository : CrudRepository<UserSettingsEntity, Int> {

    @Query(
        """
            SELECT settings.code, settings.description, users_settings.value, settings.default_value 
            FROM settings INNER JOIN users_settings
            ON settings.code = users_settings.code
            WHERE users_settings.user_id = :userId
        """
    )
    fun findUserSettingsByUserId(userId: Int): List<UserSettingsEntity>

    @Modifying
    @Query("""
            UPDATE users_settings
            SET users_settings.value = :settingValueNew
            WHERE users_settings.user_id = :userId
            AND users_settings.code = :settingCode
          """
    )
    fun updateUserSettingsByUserId(
        settingCode: String,
        settingValueNew: String,
        userId: Int,
    )
}
