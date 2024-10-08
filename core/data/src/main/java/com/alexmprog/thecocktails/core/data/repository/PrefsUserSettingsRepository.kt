package com.alexmprog.thecocktails.core.data.repository

import com.alexmprog.thecocktails.core.common.dispatchers.CommonDispatchers
import com.alexmprog.thecocktails.core.common.dispatchers.Dispatcher
import com.alexmprog.thecocktails.core.datastore.UserPrefsDataSource
import com.alexmprog.thecocktails.core.datastore.model.UserPrefs
import com.alexmprog.thecocktails.core.domain.model.UserSettings
import com.alexmprog.thecocktails.core.domain.repository.UserSettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PrefsUserSettingsRepository @Inject constructor(
    private val userPrefsDataSource: UserPrefsDataSource,
    @Dispatcher(CommonDispatchers.Default) private val defaultDispatcher: CoroutineDispatcher
) : UserSettingsRepository {

    override fun getUserSettings(): Flow<UserSettings> = userPrefsDataSource.getUserPrefs()
        .map { it.toModel() }
        .flowOn(defaultDispatcher)

    override suspend fun saveUserSettings(userSettings: UserSettings) =
        withContext(defaultDispatcher) {
            userPrefsDataSource.saveUserPrefs(userSettings.toPrefs())
        }
}

private fun UserPrefs.toModel(): UserSettings = UserSettings(useDynamicColors = useDynamicColors)

private fun UserSettings.toPrefs(): UserPrefs = UserPrefs(useDynamicColors = useDynamicColors)