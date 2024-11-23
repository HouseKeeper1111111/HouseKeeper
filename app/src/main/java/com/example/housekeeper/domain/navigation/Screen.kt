package com.example.housekeeper.domain.navigation

sealed class Screen(val route: String) {
    data object ReminderScreen: Screen(route = "reminder_screen")
    data object SettingsScreen: Screen(route = "settings_screen")
}