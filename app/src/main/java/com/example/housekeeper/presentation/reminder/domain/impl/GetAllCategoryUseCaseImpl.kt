package com.example.housekeeper.presentation.reminder.domain.impl

import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.presentation.reminder.domain.use_case.GetAllCategoryUseCase
import javax.inject.Inject

class GetAllCategoryUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllCategoryUseCase {

    override operator fun invoke() = localDataSourceRepository.getAllCategoriesFlow()
}