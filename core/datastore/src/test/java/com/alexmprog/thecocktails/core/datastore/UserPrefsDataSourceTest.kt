package com.alexmprog.thecocktails.core.datastore

import androidx.datastore.core.DataStoreFactory
import com.alexmprog.thecocktails.core.datastore.model.UserPrefsSerializer
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import kotlin.test.Test

class UserPrefsDataSourceTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: UserPrefsDataSource

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        subject = UserPrefsDataSource(tmpFolder.testUserPrefsDataStore(testScope))
    }

    @Test
    fun shouldHideBottomNavBarIsFalseByDefault() = testScope.runTest {
        assertFalse(subject.getUserPrefs().first().useBottomNavBar)
    }

    @Test
    fun shouldHideDynamicColorsAreFalseByDefault() = testScope.runTest {
        assertFalse(subject.getUserPrefs().first().useDynamicColors)
    }

//    @Test
//    fun userShouldHideOnboardingIsTrueWhenSet() = testScope.runTest {
//        subject.setShouldHideOnboarding(true)
//        assertTrue(subject.userData.first().shouldHideOnboarding)
//    }
//
}

internal fun TemporaryFolder.testUserPrefsDataStore(coroutineScope: CoroutineScope) =
    DataStoreFactory.create(
        serializer = UserPrefsSerializer,
        scope = coroutineScope,
    ) {
        newFile("user_prefs_test.json")
    }