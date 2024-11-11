package com.example.housekeeper.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    //startDestination: String = Screen.StartScreen.route
) {
//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = startDestination
//    ) {

//        composable(route = Screen.PhoneNumberScreen.route) {
//            PhoneNumberView(
//                onNavigateToEnteringTheCodeScreen = {
//                    navController.navigate(Screen.EnteringTheCodeScreen.route) {
//                        popUpTo(Screen.PhoneNumberScreen.route) {
//                            inclusive = true    // удалить экран из стека
//                            saveState = true    // сохранить состояние
//                        }
//                        restoreState = true     // восставновить состояние
//                    }
//                }
//            )
//        }
//    }
}