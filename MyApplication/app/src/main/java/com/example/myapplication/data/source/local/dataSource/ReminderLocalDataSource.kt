package com.example.myapplication.data.source.local.dataSource

import com.example.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface ReminderLocalDataSource {

    fun getAllRemindersFlow(): Flow<List<ReminderModel>>

    fun getReminderByIdFlow(id: Int): Flow<ReminderModel>

    fun getRemindersByCategoryIdFlow(categoryId: Int): Flow<List<ReminderModel>>

    suspend fun addReminder(reminderModel: ReminderModel)

    suspend fun updateReminder(reminderModel: ReminderModel)

    suspend fun deleteReminder(reminderModel: ReminderModel)
}