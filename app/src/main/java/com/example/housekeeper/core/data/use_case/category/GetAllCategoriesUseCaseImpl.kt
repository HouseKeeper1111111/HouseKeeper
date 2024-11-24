package com.example.housekeeper.core.data.use_case.category

import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.core.domain.use_case.category.GetAllCategoriesUseCase
import javax.inject.Inject

class GetAllCategoriesUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllCategoriesUseCase {

    override operator fun invoke() = localDataSourceRepository.getAllCategoriesFlow()
}