package com.org.taskmate.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddTask : Screen("add_task")
}