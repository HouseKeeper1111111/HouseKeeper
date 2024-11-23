package com.example.housekeeper.domain.constants

import android.content.Context
import android.util.DisplayMetrics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ScreenSize(
    val height: Dp,
    val width: Dp
)

class ScreenSizeProvider(context: Context) {
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics

    val height: Dp = (displayMetrics.heightPixels / displayMetrics.density).dp
    val width: Dp = (displayMetrics.widthPixels / displayMetrics.density).dp
}

@Composable
fun rememberScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    return remember(configuration) {
        val provider = ScreenSizeProvider(context)
        ScreenSize(
            height = provider.height,
            width = provider.width
        )
    }
}

