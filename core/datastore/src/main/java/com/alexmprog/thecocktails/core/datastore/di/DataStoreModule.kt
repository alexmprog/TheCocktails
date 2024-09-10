package com.alexmprog.thecocktails.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.preferencesDataStoreFile
import com.alexmprog.thecocktails.core.common.CommonDispatchers
import com.alexmprog.thecocktails.core.common.Dispatcher
import com.alexmprog.thecocktails.core.datastore.model.UserPrefs
import com.alexmprog.thecocktails.core.datastore.model.UserPrefsSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val USER_PREFS = "user_prefs.json"

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(CommonDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): DataStore<UserPrefs> = DataStoreFactory.create(serializer = UserPrefsSerializer,
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { UserPrefs() }
        ),
        scope = CoroutineScope(ioDispatcher + SupervisorJob()))
    {
        context.preferencesDataStoreFile(USER_PREFS)
    }
}