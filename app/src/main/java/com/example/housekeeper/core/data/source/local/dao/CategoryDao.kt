package com.example.housekeeper.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.housekeeper.core.data.source.local.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category")
    fun getAllCategories() : Flow<List<CategoryEntity>>

    @Insert
    fun insertCategory(reminder: CategoryEntity)

    @Update
    fun updateCategory(reminder: CategoryEntity)

    @Query("SELECT * FROM Category WHERE id=:id")
    fun getCategoryById(id: Int) : Flow<CategoryEntity>

    @Delete
    fun deleteCategory(reminder: CategoryEntity)
}