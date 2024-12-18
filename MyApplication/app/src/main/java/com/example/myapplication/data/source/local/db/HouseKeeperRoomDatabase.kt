package com.example.myapplication.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.source.local.dao.ReminderDao
import com.example.myapplication.data.source.local.dao.TimerDao
import com.example.myapplication.data.source.local.model.ReminderEntity
import com.example.myapplication.data.source.local.model.TimerEntity

@Database(
    entities = [ReminderEntity::class, TimerEntity::class],
    version = 1
)
abstract class HouseKeeperRoomDatabase : RoomDatabase() {
    abstract fun reminderDao() : ReminderDao

    abstract fun timerDao() : TimerDao
}