package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.UserSettingsRepository
import com.alexmprog.thecocktails.core.model.UserSettings
import javax.inject.Inject

class SaveUserSettingsUseCase @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository
) {
    suspend operator fun invoke(userSettings: UserSettings) =
        userSettingsRepository.saveUserSettings(userSettings)
}