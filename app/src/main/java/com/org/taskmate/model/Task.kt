package com.org.taskmate.model

/**
 * Represents a task displayed in the app.
 */
data class Task(
    val id: Int,
    val title: String,
    val category: String,
    val due: String,
    val completed: Boolean
)