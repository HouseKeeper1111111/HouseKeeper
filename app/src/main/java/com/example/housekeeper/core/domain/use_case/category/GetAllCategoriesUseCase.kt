package com.example.housekeeper.core.domain.use_case.category

import com.example.housekeeper.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetAllCategoriesUseCase {
    operator fun invoke(): Flow<List<Category>>
}