package com.example.housekeeper.core.data.mapper

import com.example.housekeeper.core.data.source.local.model.ReminderEntity
import com.example.housekeeper.core.domain.model.Reminder

fun ReminderEntity.toReminder() = Reminder(id, name, iconId, dateTime, categoryId)

fun Reminder.toReminderEntity() = ReminderEntity(id, name, iconId, dateTime, categoryId)