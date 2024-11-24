package com.example.housekeeper.core.data.mapper

import com.example.housekeeper.core.data.source.local.model.CategoryEntity
import com.example.housekeeper.core.domain.model.Category

fun CategoryEntity.toCategory() = Category(id, name, imageId)

fun Category.toCategoryEntity() = CategoryEntity(id, name, imageId)