package com.example.housekeeper.core.domain.use_case.reminder

import com.example.housekeeper.core.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface GetAllRemindersUseCase {
    operator fun invoke(): Flow<List<Reminder>>
}

interface GetReminderByIdUseCase {
    operator fun invoke(id: Int): Flow<Reminder>
}

interface GetRemindersByCategoryUseCase {
    operator fun invoke(categoryId: Int): Flow<List<Reminder>>
}

interface AddReminderUseCase {
    suspend operator fun invoke(reminder: Reminder)
}

interface DeleteReminderUseCase {
    suspend operator fun invoke(reminder: Reminder)
}

interface UpdateReminderUseCase {
    suspend operator fun invoke(reminder: Reminder)
}