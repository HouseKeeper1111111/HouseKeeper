package com.example.housekeeper.core.data.use_case.category

import com.example.housekeeper.core.domain.model.Category
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.core.domain.use_case.category.GetCategoryByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryByIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetCategoryByIdUseCase {

    override operator fun invoke(id: Int) = localDataSourceRepository.getCategoryByIdFlow(id)
}