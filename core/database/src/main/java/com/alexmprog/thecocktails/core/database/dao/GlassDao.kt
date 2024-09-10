package com.alexmprog.thecocktails.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexmprog.thecocktails.core.database.model.GlassEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GlassDao {

    @Query(value = "SELECT * FROM Glass")
    fun getGlassEntities(): Flow<List<GlassEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGlassEntities(glassEntities: List<GlassEntity>): List<Long>
}