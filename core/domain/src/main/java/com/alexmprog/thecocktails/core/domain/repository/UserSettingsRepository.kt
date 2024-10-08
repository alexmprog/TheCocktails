package com.alexmprog.thecocktails.core.domain.repository

import com.alexmprog.thecocktails.core.domain.model.UserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {

    fun getUserSettings(): Flow<UserSettings>

    suspend fun saveUserSettings(userSettings: UserSettings)
}