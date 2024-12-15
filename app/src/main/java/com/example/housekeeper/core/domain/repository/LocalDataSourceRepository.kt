package com.example.housekeeper.core.domain.repository

import com.example.housekeeper.core.domain.model.Reminder
import com.example.housekeeper.core.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {
    // Reminder
    fun getAllRemindersFlow(): Flow<List<Reminder>>

    fun getReminderByIdFlow(id: Int): Flow<Reminder>

    fun getRemindersByCategoryIdFlow(categoryId: Int): Flow<List<Reminder>>

    suspend fun addReminder(reminder: Reminder)

    suspend fun updateReminder(reminder: Reminder)

    suspend fun deleteReminder(reminder: Reminder)


    // Timer
    fun getAllTimersFlow(): Flow<List<Timer>>

    suspend fun addTimer(timer: Timer)

    suspend fun updateTimer(timer: Timer)

    suspend fun deleteTimer(timer: Timer)
}