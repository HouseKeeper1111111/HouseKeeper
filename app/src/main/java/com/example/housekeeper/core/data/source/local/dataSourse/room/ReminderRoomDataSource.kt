package com.example.housekeeper.core.data.source.local.dataSourse.room

import com.example.housekeeper.core.data.mapper.toReminder
import com.example.housekeeper.core.data.mapper.toReminderEntity
import com.example.housekeeper.core.data.source.local.dao.ReminderDao
import com.example.housekeeper.core.data.source.local.dataSourse.ReminderLocalDataSource
import com.example.housekeeper.core.domain.model.Reminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRoomDataSource @Inject constructor(
    private val reminderDao: ReminderDao
) : ReminderLocalDataSource {

    override fun getAllRemindersFlow(): Flow<List<Reminder>> {
        return reminderDao.getAllReminders().map { reminderEntityList ->
            reminderEntityList.map { reminderEntity -> reminderEntity.toReminder() }
        }
    }

    override suspend fun addReminder(reminder: Reminder) {
        reminderDao.insertReminder(reminder.toReminderEntity())
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder.toReminderEntity())
    }

    override suspend fun updateReminder(reminder: Reminder) {
        reminderDao.updateReminder(reminder.toReminderEntity())
    }

    override fun getReminderByIdFlow(id: Int): Flow<Reminder> {
        return reminderDao.getReminderById(id).map { reminderEntity ->
            reminderEntity.toReminder()
        }
    }

    override fun getRemindersByCategoryIdFlow(categoryId: Int): Flow<List<Reminder>> {
        return reminderDao.getReminderByCategoryId(categoryId).map { reminderEntityList ->
            reminderEntityList.map { reminderEntity -> reminderEntity.toReminder()
            }
        }
    }
}