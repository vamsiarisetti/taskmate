package com.org.taskmate.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.org.taskmate.data.enums.Category
import com.org.taskmate.data.enums.Priority
import com.org.taskmate.data.model.Task
import com.org.taskmate.data.repository.TaskRepository

class AddTaskViewModel : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var category by mutableStateOf(Category.PERSONAL)
        private set

    var priority by mutableStateOf(Priority.MEDIUM)
        private set

    var dueDate by mutableStateOf("")
        private set

    var reminderTime by mutableStateOf("")
        private set

    fun updateTitle(value: String) {
        title = value
    }

    fun updateDescription(value: String) {
        description = value
    }

    fun updateCategory(value: Category) {
        category = value
    }

    fun updatePriority(value: Priority) {
        priority = value
    }

    fun updateDueDate(value: String) {
        dueDate = value
    }

    fun updateReminderTime(value: String) {
        reminderTime = value
    }

    fun clearForm() {
        title = ""
        description = ""
        category = Category.PERSONAL
        priority = Priority.MEDIUM
        dueDate = ""
        reminderTime = ""
    }

    fun saveTask() {

        TaskRepository.addTask(
            Task(
                title = title,
                description = description,
                category = category,
                priority = priority,
                dueDate = dueDate,
                reminderTime = reminderTime,
                isCompleted = false
            )
        )

        clearForm()
    }
}