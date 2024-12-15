package com.example.housekeeper.core.data.mapper

import com.example.housekeeper.core.data.source.local.model.TimerEntity
import com.example.housekeeper.core.domain.model.Timer

fun TimerEntity.toTimer() = Timer(id, name, time, categoryId)

fun Timer.toTimerEntity() = TimerEntity(id, name, time, categoryId)