package com.alexmprog.thecocktails.core.domain.usecase

import com.alexmprog.thecocktails.core.domain.model.UserSettings
import com.alexmprog.thecocktails.core.domain.repository.UserSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSettingsUseCase @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository
) {
    operator fun invoke(): Flow<UserSettings> = userSettingsRepository.getUserSettings()
}