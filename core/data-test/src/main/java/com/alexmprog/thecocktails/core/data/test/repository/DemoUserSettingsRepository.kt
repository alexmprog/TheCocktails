package com.alexmprog.thecocktails.core.data.test.repository

import com.alexmprog.thecocktails.core.data.repository.UserSettingsRepository
import com.alexmprog.thecocktails.core.model.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class DemoUserSettingsRepository @Inject constructor() : UserSettingsRepository {

    private val state = MutableStateFlow(UserSettings(false))

    override fun getUserSettings(): Flow<UserSettings> = state

    override suspend fun saveUserSettings(userSettings: UserSettings) {
        state.update { userSettings }
    }
}