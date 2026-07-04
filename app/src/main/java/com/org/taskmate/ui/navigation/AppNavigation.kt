package com.org.taskmate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.org.taskmate.ui.addtask.AddTaskScreen
import com.org.taskmate.ui.home.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(
                onAddTaskClick = {
                    navController.navigate(Screen.AddTask.route)
                }
            )

        }

        composable(Screen.AddTask.route) {

            AddTaskScreen(
                onBack = {
                    navController.popBackStack()
                }
            )

        }

    }

}