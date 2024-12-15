package com.example.housekeeper.core.data.source.local.dataSourse

import com.example.housekeeper.core.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderLocalDataSource {

    fun getAllRemindersFlow(): Flow<List<Reminder>>

    fun getReminderByIdFlow(id: Int): Flow<Reminder>

    fun getRemindersByCategoryIdFlow(categoryId: Int): Flow<List<Reminder>>

    suspend fun addReminder(reminder: Reminder)

    suspend fun updateReminder(reminder: Reminder)

    suspend fun deleteReminder(reminder: Reminder)
}