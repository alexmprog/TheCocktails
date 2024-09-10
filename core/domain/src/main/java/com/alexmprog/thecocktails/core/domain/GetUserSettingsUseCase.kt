package com.alexmprog.thecocktails.core.domain

import com.alexmprog.thecocktails.core.data.repository.UserSettingsRepository
import com.alexmprog.thecocktails.core.model.UserSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSettingsUseCase @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository
) {
    operator fun invoke(): Flow<UserSettings> = userSettingsRepository.getUserSettings()
}