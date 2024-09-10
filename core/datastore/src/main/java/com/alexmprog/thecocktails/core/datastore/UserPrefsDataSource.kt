package com.alexmprog.thecocktails.core.datastore

import androidx.datastore.core.DataStore
import com.alexmprog.thecocktails.core.datastore.model.UserPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPrefsDataSource @Inject constructor(private val preferences: DataStore<UserPrefs>) {

    fun getUserPrefs(): Flow<UserPrefs> = preferences.data

    suspend fun saveUserPrefs(userPrefs: UserPrefs) {
        preferences.updateData { userPrefs }
    }
}