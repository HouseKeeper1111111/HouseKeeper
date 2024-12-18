package com.example.myapplication.data.source.local.dataSource

import com.example.myapplication.domain.model.TimerModel
import kotlinx.coroutines.flow.Flow

interface TimerLocalDataSource {

    fun getAllTimersFlow(): Flow<List<TimerModel>>

    fun getTimerByIdFlow(id: Int): Flow<TimerModel>

    fun getTimerByCategoryIdFlow(categoryId: Int): Flow<List<TimerModel>>

    suspend fun addTimer(timerModel: TimerModel)

    suspend fun updateTimer(timerModel: TimerModel)

    suspend fun deleteTimer(timerModel: TimerModel)
}