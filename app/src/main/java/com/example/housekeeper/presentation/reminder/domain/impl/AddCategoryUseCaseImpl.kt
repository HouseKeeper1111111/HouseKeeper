package com.example.housekeeper.presentation.reminder.domain.impl

import com.example.housekeeper.core.domain.model.Category
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.presentation.reminder.domain.use_case.AddCategoryUseCase
import javax.inject.Inject

class AddCategoryUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : AddCategoryUseCase {

    override suspend operator fun invoke(category: Category) {
        localDataSourceRepository.addCategory(category)
    }
}