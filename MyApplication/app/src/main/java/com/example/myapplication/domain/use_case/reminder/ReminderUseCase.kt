package com.example.myapplication.domain.use_case.reminder

import com.example.myapplication.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface GetAllRemindersUseCase {
    operator fun invoke(): Flow<List<ReminderModel>>
}

interface GetReminderByIdUseCase {
    operator fun invoke(id: Int): Flow<ReminderModel>
}

interface GetRemindersByCategoryUseCase {
    operator fun invoke(categoryId: Int): Flow<List<ReminderModel>>
}

interface AddReminderUseCase {
    suspend operator fun invoke(reminder: ReminderModel)
}

interface DeleteReminderUseCase {
    suspend operator fun invoke(reminder: ReminderModel)
}

interface UpdateReminderUseCase {
    suspend operator fun invoke(reminder: ReminderModel)
}