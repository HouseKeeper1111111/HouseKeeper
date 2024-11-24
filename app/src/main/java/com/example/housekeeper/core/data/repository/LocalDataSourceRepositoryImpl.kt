package com.example.housekeeper.core.data.repository

import com.example.housekeeper.core.data.source.local.dataSourse.CategoryLocalDataSource
import com.example.housekeeper.core.data.source.local.dataSourse.ReminderLocalDataSource
import com.example.housekeeper.core.data.source.local.dataSourse.TimerLocalDataSource
import com.example.housekeeper.core.domain.model.Category
import com.example.housekeeper.core.domain.model.Reminder
import com.example.housekeeper.core.domain.model.Timer
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val categoryDataSource: CategoryLocalDataSource,
    private val reminderDataSource: ReminderLocalDataSource,
    private val timerDataSource: TimerLocalDataSource
) : LocalDataSourceRepository {

    // Category
    override fun getAllCategoriesFlow() = categoryDataSource.getAllCategoriesFlow()

    override suspend fun addCategory(category: Category) = categoryDataSource.addCategory(category)

    override suspend fun updateCategory(category: Category) = categoryDataSource.updateCategory(category)

    override suspend fun deleteCategory(category: Category) = categoryDataSource.deleteCategory(category)


    // Reminder
    override fun getAllRemindersFlow() = reminderDataSource.getAllRemindersFlow()

    override fun getReminderByIdFlow(id: Int) = reminderDataSource.getReminderByIdFlow(id)

    override fun getRemindersByCategoryIdFlow(categoryId: Int) = reminderDataSource.getRemindersByCategoryIdFlow(categoryId)

    override suspend fun addReminder(reminder: Reminder) = reminderDataSource.addReminder(reminder)

    override suspend fun updateReminder(reminder: Reminder) = reminderDataSource.updateReminder(reminder)

    override suspend fun deleteReminder(reminder: Reminder) = reminderDataSource.deleteReminder(reminder)


    // Timer
    override fun getAllTimersFlow() = timerDataSource.getAllTimersFlow()

    override suspend fun addTimer(timer: Timer) = timerDataSource.addTimer(timer)

    override suspend fun updateTimer(timer: Timer) = timerDataSource.updateTimer(timer)

    override suspend fun deleteTimer(timer: Timer) = timerDataSource.deleteTimer(timer)
}