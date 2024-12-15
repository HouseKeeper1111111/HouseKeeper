package com.example.housekeeper.core.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housekeeper.ui.presentation.pages.reminder.ReminderView

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.ReminderScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.ReminderScreen.route) {
            ReminderView(
                navController = navController,
                onNavigateToSettingsScreen = {
//                    navController.navigate(Screen.SettingsScreen.route) {
//                        popUpTo(Screen.ReminderScreen.route) {
//                            inclusive = true    // удалить экран из стека
//                            saveState = true    // сохранить состояние
//                        }
//                        restoreState = true     // восставновить состояние
//                    }
                }
            )
        }
    }
}