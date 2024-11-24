package com.example.housekeeper.presentation.reminder.domain.use_case

import com.example.housekeeper.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetAllCategoryUseCase {
    operator fun invoke(): Flow<List<Category>>
}