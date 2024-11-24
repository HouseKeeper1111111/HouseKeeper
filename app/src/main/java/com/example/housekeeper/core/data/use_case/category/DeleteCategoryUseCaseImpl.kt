package com.example.housekeeper.core.data.use_case.category

import com.example.housekeeper.core.domain.model.Category
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.core.domain.use_case.category.DeleteCategoryUseCase
import javax.inject.Inject

class DeleteCategoryUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteCategoryUseCase {

    override suspend operator fun invoke(category: Category) {
        localDataSourceRepository.deleteCategory(category)
    }
}