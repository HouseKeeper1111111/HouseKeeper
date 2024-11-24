package com.example.housekeeper.presentation.reminder.domain.use_case

import com.example.housekeeper.core.domain.model.Category

interface AddCategoryUseCase {
    suspend operator fun invoke(category: Category)
}