package com.example.housekeeper.core.data.source.local.dataSourse.room

import com.example.housekeeper.core.data.mapper.toCategory
import com.example.housekeeper.core.data.mapper.toCategoryEntity
import com.example.housekeeper.core.data.source.local.dao.CategoryDao
import com.example.housekeeper.core.data.source.local.dataSourse.CategoryLocalDataSource
import com.example.housekeeper.core.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRoomDataSource @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryLocalDataSource {

    override fun getAllCategoriesFlow(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { categoryEntityList ->
            categoryEntityList.map { categoryEntity -> categoryEntity.toCategory() }
        }
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.insertCategory(category.toCategoryEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.toCategoryEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.toCategoryEntity())
    }

    override fun getCategoryByIdFlow(id: Int): Flow<Category> {
        return categoryDao.getCategoryById(id).map { categoryEntity ->
            categoryEntity.toCategory()
        }
    }
}