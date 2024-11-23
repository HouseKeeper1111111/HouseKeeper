package com.example.housekeeper.domain.navigation

sealed class Screen(val route: String) {
    data object ReminderScreen: Screen(route = "reminder_screen")
    data object TimerScreen: Screen(route = "timer_screen")
    data object CleanerScreen: Screen(route = "cleaner_screen")
    data object SettingsScreen: Screen(route = "settings_screen")
}