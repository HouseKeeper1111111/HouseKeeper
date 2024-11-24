package com.example.housekeeper.core.data.use_case.reminder

import com.example.housekeeper.core.domain.model.Reminder
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import com.example.housekeeper.core.domain.use_case.reminder.AddReminderUseCase
import com.example.housekeeper.core.domain.use_case.reminder.DeleteReminderUseCase
import com.example.housekeeper.core.domain.use_case.reminder.GetAllRemindersUseCase
import com.example.housekeeper.core.domain.use_case.reminder.GetReminderByIdUseCase
import com.example.housekeeper.core.domain.use_case.reminder.GetRemindersByCategoryUseCase
import com.example.housekeeper.core.domain.use_case.reminder.UpdateReminderUseCase
import javax.inject.Inject

class GetAllRemindersUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllRemindersUseCase {

    override operator fun invoke() = localDataSourceRepository.getAllRemindersFlow()
}


class GetReminderByIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetReminderByIdUseCase {

    override operator fun invoke(id: Int) = localDataSourceRepository.getReminderByIdFlow(id)
}


class GetRemindersByCategoryIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetRemindersByCategoryUseCase {

    override operator fun invoke(categoryId: Int) = localDataSourceRepository.getRemindersByCategoryIdFlow(categoryId)
}


class AddReminderUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : AddReminderUseCase {

    override suspend operator fun invoke(reminder: Reminder) {
        localDataSourceRepository.addReminder(reminder)
    }
}


class DeleteReminderUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteReminderUseCase {

    override suspend operator fun invoke(reminder: Reminder) {
        localDataSourceRepository.deleteReminder(reminder)
    }
}


class UpdateReminderUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : UpdateReminderUseCase {

    override suspend operator fun invoke(reminder: Reminder) {
        localDataSourceRepository.updateReminder(reminder)
    }
}