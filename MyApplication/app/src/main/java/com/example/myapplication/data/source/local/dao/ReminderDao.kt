package com.example.myapplication.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.source.local.model.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM Reminder")
    fun getAllReminders() : Flow<List<ReminderEntity>>

    @Insert
    fun insertReminder(reminder: ReminderEntity)

    @Update
    fun updateReminder(reminder: ReminderEntity)

    @Query("SELECT * FROM Reminder WHERE id=:id")
    fun getReminderById(id: Int) : Flow<ReminderEntity>

    @Query("SELECT * FROM Reminder WHERE categoryId=:categoryId")
    fun getReminderByCategoryId(categoryId: Int): Flow<List<ReminderEntity>>

    @Delete
    fun deleteReminder(reminder: ReminderEntity)
}