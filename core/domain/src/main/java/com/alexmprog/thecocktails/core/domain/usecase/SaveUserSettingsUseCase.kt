package com.alexmprog.thecocktails.core.domain.usecase

import com.alexmprog.thecocktails.core.domain.model.UserSettings
import com.alexmprog.thecocktails.core.domain.repository.UserSettingsRepository
import javax.inject.Inject

class SaveUserSettingsUseCase @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository
) {
    suspend operator fun invoke(userSettings: UserSettings) =
        userSettingsRepository.saveUserSettings(userSettings)
}