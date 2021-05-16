package com.example.work.repository

import com.example.work.entity.UserSettingsEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface UserSettingsRepository : CrudRepository<UserSettingsEntity, Int> {

    @Query(
        """
            SELECT settings.code, settings.description, settings_users.value, settings.default_value 
            FROM settings INNER JOIN settings_users
            ON settings.id = settings_users.setting_id
            WHERE settings_users.user_id = :userId
        """
    )
    fun findUserSettingsByUserId(userId: Int): List<UserSettingsEntity>
}
