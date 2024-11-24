package com.example.housekeeper.core.data.source.local.dataSourse

import com.example.housekeeper.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    fun getAllCategoriesFlow(): Flow<List<Category>>

    fun getCategoryByIdFlow(id: Int): Flow<Category>

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)
}