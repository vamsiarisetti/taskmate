package com.org.taskmate.viewmodel

import androidx.lifecycle.ViewModel
import com.org.taskmate.data.enums.Category
import com.org.taskmate.data.enums.Priority
import com.org.taskmate.data.model.Task
import com.org.taskmate.data.repository.TaskRepository

class HomeViewModel : ViewModel() {

    val tasks = TaskRepository.getTasks()
    /*val tasks = listOf(

        Task(
            id = 1,
            title = "Pay Electricity Bill",
            description = "Electricity bill reminder",
            category = Category.BILLS,
            priority = Priority.HIGH,
            dueDate = "Today",
            reminderTime = "6:00 PM",
            isCompleted = false
        ),

        Task(
            id = 2,
            title = "Buy Groceries",
            description = "Milk, Bread, Eggs",
            category = Category.SHOPPING,
            priority = Priority.MEDIUM,
            dueDate = "Today",
            reminderTime = "7:00 PM",
            isCompleted = true
        ),

        Task(
            id = 3,
            title = "Team Meeting",
            description = "Sprint Planning",
            category = Category.WORK,
            priority = Priority.LOW,
            dueDate = "Tomorrow",
            reminderTime = "10:00 AM",
            isCompleted = false
        )

    )*/

}