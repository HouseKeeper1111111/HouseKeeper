package com.example.myapplication.domain.model

data class ReminderModel(
    val id: Int,
    val name: String,
    val iconPath: String,
    val dateTime: Long,
    val categoryId: Int
)
