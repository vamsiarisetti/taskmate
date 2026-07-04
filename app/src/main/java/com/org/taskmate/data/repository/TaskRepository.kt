package com.org.taskmate.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.org.taskmate.data.model.Task

object TaskRepository {

    private val taskList = mutableStateListOf<Task>()

    fun getTasks(): List<Task> {
        return taskList
    }

    fun addTask(task: Task) {
        taskList.add(task)
    }

    fun deleteTask(task: Task) {
        taskList.remove(task)
    }

    fun clear() {
        taskList.clear()
    }
}