package com.example.housekeeper.ui.router

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.housekeeper.R
import com.example.housekeeper.domain.navigation.Screen
import com.example.housekeeper.ui.theme.MidnightVelvet

@Composable
fun Router(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MidnightVelvet
    ) {
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { navController.navigate(Screen.ReminderScreen.route) }
        ) {
            Image(
                painter = painterResource(R.drawable.reminder_active_icon),
                contentDescription = "Reminder active icon"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(R.drawable.timer_no_active_icon),
                contentDescription = "Reminder active icon"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(R.drawable.cleaner_no_active_icon),
                contentDescription = "Reminder active icon"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(R.drawable.settings_no_active_icon),
                contentDescription = "Reminder active icon"
            )
        }
    }
}