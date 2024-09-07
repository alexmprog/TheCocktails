package com.alexmprog.thecocktails.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexmprog.thecocktails.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query(value = "SELECT * FROM Category")
    fun getCategoryEntities(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategoryEntities(categoryEntities: List<CategoryEntity>): List<Long>
}