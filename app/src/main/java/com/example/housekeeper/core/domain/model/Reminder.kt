package com.example.housekeeper.core.domain.model

data class Reminder(
    val id: Int,
    val name: String,
    val iconPath: String,
    val dateTime: Long,
    val categoryId: Int
)
