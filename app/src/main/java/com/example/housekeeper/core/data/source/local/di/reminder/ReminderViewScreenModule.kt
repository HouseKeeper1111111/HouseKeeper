package com.example.housekeeper.core.data.source.local.di.reminder

import com.example.housekeeper.core.data.use_case.category.AddCategoryUseCaseImpl
import com.example.housekeeper.core.data.use_case.category.GetAllCategoriesUseCaseImpl
import com.example.housekeeper.core.domain.use_case.category.AddCategoryUseCase
import com.example.housekeeper.core.domain.use_case.category.GetAllCategoriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReminderViewScreenModule{

//    @Binds
//    abstract fun bindAddCategoryUseCase(
//        addCategoryUseCaseImpl: AddCategoryUseCaseImpl
//    ) : AddCategoryUseCase
//
//    @Binds
//    abstract fun  bindGetAllCategories(
//        getAllCategoriesUseCaseImpl: GetAllCategoriesUseCaseImpl
//    ) : GetAllCategoriesUseCase
}