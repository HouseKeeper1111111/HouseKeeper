package com.example.myapplication.data.mapper

import com.example.myapplication.data.source.local.model.TimerEntity
import com.example.myapplication.domain.model.TimerModel

fun TimerEntity.toTimer() = TimerModel(id, name, time, categoryId)

fun TimerModel.toTimerEntity() = TimerEntity(id, name, time, categoryId)