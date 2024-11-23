package com.example.housekeeper.ui.router

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.housekeeper.domain.constants.DrawableConstants
import com.example.housekeeper.domain.navigation.Screen
import com.example.housekeeper.ui.theme.MidnightVelvet

@Composable
fun Router(navController: NavHostController) {
    var currentScreen by remember { mutableStateOf(Screen.ReminderScreen.route) }

    BottomAppBar(
        containerColor = MidnightVelvet
    ) {
        Spacer(modifier = Modifier.weight(1f))
        IconToggleButton(
            modifier = Modifier.size(45.dp),
            checked = currentScreen == Screen.ReminderScreen.route,
            onCheckedChange = {
                if (!it) return@IconToggleButton
                currentScreen = Screen.ReminderScreen.route
                navController.navigate(Screen.ReminderScreen.route)
            }
        ) {
            val reminderIcon = if (currentScreen == Screen.ReminderScreen.route) {
                DrawableConstants.REMINDER_ACTIVE_ICON
            } else {
                DrawableConstants.REMINDER_NO_ACTIVE_ICON
            }
            Image(
                painter = painterResource(reminderIcon),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconToggleButton(
            modifier = Modifier.size(45.dp),
            checked = currentScreen == Screen.TimerScreen.route,
            onCheckedChange = {
                if (!it) return@IconToggleButton
//                currentScreen = Screen.TimerScreen.route
//                navController.navigate(Screen.TimerScreen.route)
            }
        ) {
            val timerIcon = if (currentScreen == Screen.TimerScreen.route) {
                DrawableConstants.TIMER_ACTIVE_ICON
            } else {
                DrawableConstants.TIMER_NO_ACTIVE_ICON
            }
            Image(
                painter = painterResource(timerIcon),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconToggleButton(
            modifier = Modifier.size(45.dp),
            checked = currentScreen == Screen.CleanerScreen.route,
            onCheckedChange = {
                if (!it) return@IconToggleButton
//                currentScreen = Screen.CleanerScreen.route
//                navController.navigate(Screen.CleanerScreen.route)
            }
        ) {
            val cleanerIcon = if (currentScreen == Screen.CleanerScreen.route) {
                DrawableConstants.CLEANER_ACTIVE_ICON
            } else {
                DrawableConstants.CLEANER_NO_ACTIVE_ICON
            }
            Image(
                painter = painterResource(cleanerIcon),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconToggleButton(
            modifier = Modifier.size(45.dp),
            checked = currentScreen == Screen.SettingsScreen.route,
            onCheckedChange = {
                if (!it) return@IconToggleButton
//                currentScreen = Screen.SettingsScreen.route
//                navController.navigate(Screen.SettingsScreen.route)
            }
        ) {
            val settingsIcon = if (currentScreen == Screen.SettingsScreen.route) {
                DrawableConstants.SETTINGS_ACTIVE_ICON
            } else {
                DrawableConstants.SETTINGS_NO_ACTIVE_ICON
            }
            Image(
                painter = painterResource(settingsIcon),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
@Preview
private fun RouterPreview() {
    Router(navController = rememberNavController())
}