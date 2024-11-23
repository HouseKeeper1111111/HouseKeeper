package com.example.housekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.housekeeper.domain.navigation.NavigationGraph
import com.example.housekeeper.ui.theme.HouseKeeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()

            HouseKeeperTheme {
                NavigationGraph(navController = navigationController)
            }
        }
    }
}
