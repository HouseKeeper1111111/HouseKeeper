package com.example.housekeeper.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.housekeeper.core.data.source.local.model.TimerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timer")
    fun getAllTimers() : Flow<List<TimerEntity>>

    @Insert
    fun insertTimer(reminder: TimerEntity)

    @Update
    fun updateTimer(reminder: TimerEntity)

    @Query("SELECT * FROM Timer WHERE id=:id")
    fun getTimerById(id: Int) : Flow<TimerEntity>

    @Query("SELECT * FROM Timer WHERE categoryId=:categoryId")
    fun getTimerByCategoryId(categoryId: Int): Flow<List<TimerEntity>>

    @Delete
    fun deleteTimer(reminder: TimerEntity)
}