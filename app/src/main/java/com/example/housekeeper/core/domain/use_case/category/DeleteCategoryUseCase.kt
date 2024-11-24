package com.example.housekeeper.core.domain.use_case.category

import com.example.housekeeper.core.domain.model.Category

interface DeleteCategoryUseCase {
    suspend operator fun invoke(category: Category)
}