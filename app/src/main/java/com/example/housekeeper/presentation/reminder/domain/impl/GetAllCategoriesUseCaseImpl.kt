package com.example.housekeeper.presentation.reminder.domain.impl

import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.presentation.reminder.domain.use_case.GetAllCategoriesUseCase
import javax.inject.Inject

class GetAllCategoriesUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllCategoriesUseCase {

    override operator fun invoke() = localDataSourceRepository.getAllCategoriesFlow()
}