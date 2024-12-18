package com.example.myapplication.data.source.local.dataSource.room

import com.example.myapplication.data.mapper.toReminder
import com.example.myapplication.data.mapper.toReminderEntity
import com.example.myapplication.data.source.local.dao.ReminderDao
import com.example.myapplication.data.source.local.dataSource.ReminderLocalDataSource
import com.example.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRoomDataSource @Inject constructor(
    private val reminderDao: ReminderDao
) : ReminderLocalDataSource {

    override fun getAllRemindersFlow(): Flow<List<ReminderModel>> {
        return reminderDao.getAllReminders().map { reminderEntityList ->
            reminderEntityList.map { reminderEntity -> reminderEntity.toReminder() }
        }
    }

    override suspend fun addReminder(reminderModel: ReminderModel) {
        reminderDao.insertReminder(reminderModel.toReminderEntity())
    }

    override suspend fun deleteReminder(reminderModel: ReminderModel) {
        reminderDao.deleteReminder(reminderModel.toReminderEntity())
    }

    override suspend fun updateReminder(reminderModel: ReminderModel) {
        reminderDao.updateReminder(reminderModel.toReminderEntity())
    }

    override fun getReminderByIdFlow(id: Int): Flow<ReminderModel> {
        return reminderDao.getReminderById(id).map { reminderEntity ->
            reminderEntity.toReminder()
        }
    }

    override fun getRemindersByCategoryIdFlow(categoryId: Int): Flow<List<ReminderModel>> {
        return reminderDao.getReminderByCategoryId(categoryId).map { reminderEntityList ->
            reminderEntityList.map { reminderEntity -> reminderEntity.toReminder()
            }
        }
    }
}