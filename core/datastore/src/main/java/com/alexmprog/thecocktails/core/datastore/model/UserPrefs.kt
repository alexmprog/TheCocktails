package com.alexmprog.thecocktails.core.datastore.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class UserPrefs(
    val useBottomNavBar: Boolean = false,
    val useDynamicColors: Boolean = false
)

internal object UserPrefsSerializer : Serializer<UserPrefs> {

    override val defaultValue = UserPrefs(
        useBottomNavBar = false, useDynamicColors = false
    )

    override suspend fun readFrom(input: InputStream): UserPrefs {
        try {
            return Json.decodeFromString(
                UserPrefs.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read UserPrefs", serialization)
        }
    }

    override suspend fun writeTo(t: UserPrefs, output: OutputStream) {
        output.write(
            Json.encodeToString(UserPrefs.serializer(), t)
                .encodeToByteArray()
        )
    }
}