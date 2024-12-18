package com.example.myapplication.data.source.local.dataSource.room

import com.example.myapplication.data.mapper.toTimer
import com.example.myapplication.data.mapper.toTimerEntity
import com.example.myapplication.data.source.local.dao.TimerDao
import com.example.myapplication.data.source.local.dataSource.TimerLocalDataSource
import com.example.myapplication.domain.model.TimerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerRoomDataSource @Inject constructor(
    private val timerDao: TimerDao
) : TimerLocalDataSource {

    override fun getAllTimersFlow(): Flow<List<TimerModel>> {
        return timerDao.getAllTimers().map { timerEntityList ->
            timerEntityList.map { timerEntity -> timerEntity.toTimer() }
        }
    }

    override suspend fun addTimer(timerModel: TimerModel) {
        timerDao.insertTimer(timerModel.toTimerEntity())
    }

    override suspend fun deleteTimer(timerModel: TimerModel) {
        timerDao.deleteTimer(timerModel.toTimerEntity())
    }

    override suspend fun updateTimer(timerModel: TimerModel) {
        timerDao.updateTimer(timerModel.toTimerEntity())
    }

    override fun getTimerByIdFlow(id: Int): Flow<TimerModel> {
        return timerDao.getTimerById(id).map { reminderEntity ->
            reminderEntity.toTimer()
        }
    }

    override fun getTimerByCategoryIdFlow(categoryId: Int): Flow<List<TimerModel>> {
        return timerDao.getTimerByCategoryId(categoryId).map { timerEntityList ->
            timerEntityList.map { timerEntity -> timerEntity.toTimer() }
        }
    }
}