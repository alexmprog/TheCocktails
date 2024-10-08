package com.alexmprog.thecocktails.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.alexmprog.thecocktails.core.common.dispatchers.CommonDispatchers
import com.alexmprog.thecocktails.core.common.dispatchers.Dispatcher
import com.alexmprog.thecocktails.core.common.di.ApplicationScope
import com.alexmprog.thecocktails.core.datastore.model.UserPrefs
import com.alexmprog.thecocktails.core.datastore.model.UserPrefsSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

private const val USER_PREFS = "user_prefs.json"

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
        @ApplicationScope coroutineScope: CoroutineScope,
        @Dispatcher(CommonDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): DataStore<UserPrefs> = DataStoreFactory.create(
        serializer = UserPrefsSerializer,
        scope = CoroutineScope(coroutineScope.coroutineContext + ioDispatcher)
    )
    {
        context.preferencesDataStoreFile(USER_PREFS)
    }
}