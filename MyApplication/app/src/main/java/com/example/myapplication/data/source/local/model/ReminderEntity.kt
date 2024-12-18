package com.example.myapplication.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "icon_path") val iconPath: String,
    @ColumnInfo(name = "date_time") val dateTime: Long,
    @ColumnInfo(name = "category_id", index = true) val categoryId: Int
)
