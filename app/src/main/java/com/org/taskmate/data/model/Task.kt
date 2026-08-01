package com.org.taskmate.data.model

import com.org.taskmate.data.enums.Category
import com.org.taskmate.data.enums.Priority

/**
 * Represents a task displayed in the app.
 */
data class Task(
    val id: Long = 0,
    val title: String,
    val description: String,
    val category: Category,
    val priority: Priority,
    val dueDate: String? = null,
    val reminderTime: String? = null,
    val isCompleted: Boolean = false
)