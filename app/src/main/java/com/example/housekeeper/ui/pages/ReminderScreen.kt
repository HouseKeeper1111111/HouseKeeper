package com.example.housekeeper.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.housekeeper.ui.theme.WhiteLilac
import com.example.housekeeper.ui.theme.rememberScreenSize
import com.example.housekeeper.ui.theme.UbuntuFontFamily

@Composable
fun ReminderScreen() {
    val screenSize = rememberScreenSize()

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = WhiteLilac)
                    .padding(paddingValues)
                    .padding(top = (screenSize.height.value * 0.03).dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your reminders",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = UbuntuFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    )
}


@Composable
@Preview(device = Devices.PIXEL_7_PRO)
private fun ReminderScreenPreview() {
    ReminderScreen()
}