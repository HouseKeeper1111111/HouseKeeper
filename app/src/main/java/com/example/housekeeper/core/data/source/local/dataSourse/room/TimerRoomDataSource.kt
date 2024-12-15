package com.example.housekeeper.core.data.source.local.dataSourse.room

import com.example.housekeeper.core.data.mapper.toTimer
import com.example.housekeeper.core.data.mapper.toTimerEntity
import com.example.housekeeper.core.data.source.local.dao.TimerDao
import com.example.housekeeper.core.data.source.local.dataSourse.TimerLocalDataSource
import com.example.housekeeper.core.domain.model.Timer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerRoomDataSource @Inject constructor(
    private val timerDao: TimerDao
) : TimerLocalDataSource {

    override fun getAllTimersFlow(): Flow<List<Timer>> {
        return timerDao.getAllTimers().map { timerEntityList ->
            timerEntityList.map { timerEntity -> timerEntity.toTimer() }
        }
    }

    override suspend fun addTimer(timer: Timer) {
        timerDao.insertTimer(timer.toTimerEntity())
    }

    override suspend fun deleteTimer(timer: Timer) {
        timerDao.deleteTimer(timer.toTimerEntity())
    }

    override suspend fun updateTimer(timer: Timer) {
        timerDao.updateTimer(timer.toTimerEntity())
    }

    override fun getTimerByIdFlow(id: Int): Flow<Timer> {
        return timerDao.getTimerById(id).map { reminderEntity ->
            reminderEntity.toTimer()
        }
    }

    override fun getTimerByCategoryIdFlow(categoryId: Int): Flow<List<Timer>> {
        return timerDao.getTimerByCategoryId(categoryId).map { timerEntityList ->
            timerEntityList.map { timerEntity -> timerEntity.toTimer() }
        }
    }
}