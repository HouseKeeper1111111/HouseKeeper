package com.example.housekeeper.core.data.use_case.category

import com.example.housekeeper.core.domain.model.Category
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.core.domain.use_case.category.AddCategoryUseCase
import com.example.housekeeper.core.domain.use_case.category.DeleteCategoryUseCase
import com.example.housekeeper.core.domain.use_case.category.GetAllCategoriesUseCase
import com.example.housekeeper.core.domain.use_case.category.GetCategoryByIdUseCase
import com.example.housekeeper.core.domain.use_case.category.UpdateCategoryUseCase
import javax.inject.Inject


class GetAllCategoriesUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllCategoriesUseCase {

    override operator fun invoke() = localDataSourceRepository.getAllCategoriesFlow()
}


class GetCategoryByIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetCategoryByIdUseCase {

    override operator fun invoke(id: Int) = localDataSourceRepository.getCategoryByIdFlow(id)
}


class AddCategoryUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : AddCategoryUseCase {

    override suspend operator fun invoke(category: Category) {
        localDataSourceRepository.addCategory(category)
    }
}


class DeleteCategoryUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteCategoryUseCase {

    override suspend operator fun invoke(category: Category) {
        localDataSourceRepository.deleteCategory(category)
    }
}


class UpdateCategoryUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : UpdateCategoryUseCase {
    override suspend operator fun invoke(category: Category) {
        localDataSourceRepository.updateCategory(category)
    }
}