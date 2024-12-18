package com.example.myapplication.data.repository

import com.example.myapplication.data.source.local.dataSource.ReminderLocalDataSource
import com.example.myapplication.data.source.local.dataSource.TimerLocalDataSource
import com.example.myapplication.domain.model.ReminderModel
import com.example.myapplication.domain.model.TimerModel
import com.example.myapplication.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val reminderDataSource: ReminderLocalDataSource,
    private val timerDataSource: TimerLocalDataSource
) : LocalDataSourceRepository {
    // Reminder
    override fun getAllRemindersFlow() = reminderDataSource.getAllRemindersFlow()

    override fun getReminderByIdFlow(id: Int) = reminderDataSource.getReminderByIdFlow(id)

    override fun getRemindersByCategoryIdFlow(categoryId: Int) = reminderDataSource.getRemindersByCategoryIdFlow(categoryId)

    override suspend fun addReminder(reminder: ReminderModel) = reminderDataSource.addReminder(reminder)

    override suspend fun updateReminder(reminder: ReminderModel) = reminderDataSource.updateReminder(reminder)

    override suspend fun deleteReminder(reminder: ReminderModel) = reminderDataSource.deleteReminder(reminder)


    // Timer
    override fun getAllTimersFlow() = timerDataSource.getAllTimersFlow()

    override suspend fun addTimer(timerModel: TimerModel) = timerDataSource.addTimer(timerModel)

    override suspend fun updateTimer(timerModel: TimerModel) = timerDataSource.updateTimer(timerModel)

    override suspend fun deleteTimer(timerModel: TimerModel) = timerDataSource.deleteTimer(timerModel)
}