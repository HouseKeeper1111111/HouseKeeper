package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.TimerModel
import com.example.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {
    // Reminder
    fun getAllRemindersFlow(): Flow<List<ReminderModel>>

    fun getReminderByIdFlow(id: Int): Flow<ReminderModel>

    fun getRemindersByCategoryIdFlow(categoryId: Int): Flow<List<ReminderModel>>

    suspend fun addReminder(reminder: ReminderModel)

    suspend fun updateReminder(reminder: ReminderModel)

    suspend fun deleteReminder(reminder: ReminderModel)


    // Timer
    fun getAllTimersFlow(): Flow<List<TimerModel>>

    suspend fun addTimer(timerModel: TimerModel)

    suspend fun updateTimer(timerModel: TimerModel)

    suspend fun deleteTimer(timerModel: TimerModel)
}