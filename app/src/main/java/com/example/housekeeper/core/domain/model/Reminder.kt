package com.example.housekeeper.core.domain.model

import androidx.annotation.DrawableRes

data class Reminder(
    val id: Int,
    val name: String,
    @DrawableRes val iconId: Int,
    val dateTime: Long,
    val categoryId: Int
)
