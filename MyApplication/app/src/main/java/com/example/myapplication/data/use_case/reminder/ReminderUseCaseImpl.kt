package com.example.myapplication.data.use_case.reminder

import com.example.myapplication.domain.model.ReminderModel
import com.example.myapplication.domain.repository.LocalDataSourceRepository
import com.example.myapplication.domain.use_case.reminder.AddReminderUseCase
import com.example.myapplication.domain.use_case.reminder.DeleteReminderUseCase
import com.example.myapplication.domain.use_case.reminder.GetAllRemindersUseCase
import com.example.myapplication.domain.use_case.reminder.GetReminderByIdUseCase
import com.example.myapplication.domain.use_case.reminder.GetRemindersByCategoryUseCase
import com.example.myapplication.domain.use_case.reminder.UpdateReminderUseCase
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

    override suspend operator fun invoke(reminderModel: ReminderModel) {
        localDataSourceRepository.addReminder(reminderModel)
    }
}


class DeleteReminderUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteReminderUseCase {

    override suspend operator fun invoke(reminderModel: ReminderModel) {
        localDataSourceRepository.deleteReminder(reminderModel)
    }
}


class UpdateReminderUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : UpdateReminderUseCase {

    override suspend operator fun invoke(reminderModel: ReminderModel) {
        localDataSourceRepository.updateReminder(reminderModel)
    }
}