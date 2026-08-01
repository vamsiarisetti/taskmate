package com.org.taskmate.data.model

data class DashboardState(
    val total: Int = 0,
    val completed: Int = 0,
    val pending: Int = 0,
    val dueToday: Int = 0
)
