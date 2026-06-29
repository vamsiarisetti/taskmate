package com.org.taskmate.model

data class Task(
    val id: Int,
    val title: String,
    val category: String,
    val due: String,
    val completed: Boolean
)