package com.example.housekeeper.core.domain.model

import androidx.annotation.DrawableRes

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val imageId: Int
)
