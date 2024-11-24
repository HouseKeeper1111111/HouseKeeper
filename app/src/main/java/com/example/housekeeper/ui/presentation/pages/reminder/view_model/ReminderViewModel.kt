package com.example.housekeeper.ui.presentation.pages.reminder.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.core.domain.model.Category
import com.example.housekeeper.core.domain.use_case.category.AddCategoryUseCase
import com.example.housekeeper.core.domain.use_case.category.GetAllCategoriesUseCase
import com.example.housekeeper.core.domain.use_case.reminder.GetAllRemindersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface ReminderViewEvent {
    data class OnAddClick(val category: Category) : ReminderViewEvent
}

internal sealed interface ReminderViewState {
    data object Empty: ReminderViewState
    data class Content(val categories: List<Category>): ReminderViewState
}


@HiltViewModel
internal class ReminderViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase,
    getAllCategoriesUseCase: GetAllCategoriesUseCase,
    getAllRemindersUseCase: GetAllRemindersUseCase
) : ViewModel() {

    private val categories = getAllCategoriesUseCase()
    private val reminders = getAllRemindersUseCase()

    fun handleEvent(event: ReminderViewEvent) {
        when(event) {
            is ReminderViewEvent.OnAddClick -> addCategory(event.category)
        }
    }

    private fun addCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            addCategoryUseCase(category)
        }
    }

    val viewState: StateFlow<ReminderViewState> = categories.map { categoriesList ->
        if(categoriesList.isNotEmpty())
            ReminderViewState.Content(categoriesList)
        else ReminderViewState.Empty
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ReminderViewState.Empty
    )
}