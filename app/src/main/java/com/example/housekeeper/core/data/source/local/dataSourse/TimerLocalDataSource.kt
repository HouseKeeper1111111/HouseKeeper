package com.example.housekeeper.core.data.source.local.dataSourse

import com.example.housekeeper.core.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface TimerLocalDataSource {

    fun getAllTimersFlow(): Flow<List<Timer>>

    fun getTimerByIdFlow(id: Int): Flow<Timer>

    fun getTimerByCategoryIdFlow(categoryId: Int): Flow<List<Timer>>

    suspend fun addTimer(timer: Timer)

    suspend fun updateTimer(timer: Timer)

    suspend fun deleteTimer(timer: Timer)
}