package com.org.taskmate.ui.home

import com.org.taskmate.model.Task

object HomeViewModel {

    val tasks = listOf(
        Task(
            1,
            "Pay Electricity Bill",
            "Bills",
            "Today",
            false
        ),
        Task(
            2,
            "Buy Groceries",
            "Shopping",
            "Today 7 PM",
            false
        ),
        Task(
            3,
            "Team Meeting",
            "Work",
            "Tomorrow 10 AM",
            false
        )
    )
}