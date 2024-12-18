package com.example.myapplication.data.mapper

import com.example.myapplication.data.source.local.model.ReminderEntity
import com.example.myapplication.domain.model.ReminderModel

fun ReminderEntity.toReminder() = ReminderModel(id, name, iconPath, dateTime, categoryId)

fun ReminderModel.toReminderEntity() = ReminderEntity(id, name, iconPath, dateTime, categoryId)