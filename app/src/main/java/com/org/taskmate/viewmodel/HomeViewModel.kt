package com.org.taskmate.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.taskmate.data.model.Task
import com.org.taskmate.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.org.taskmate.data.repository.TaskRepository

class HomeViewModel(
    private val repository: TaskRepository
) : ViewModel() {

//    private val repository =
//        AppContainer.repository(application)

    private val _tasks =
        MutableStateFlow<List<Task>>(emptyList())

    val tasks: StateFlow<List<Task>> =
        _tasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {

        viewModelScope.launch {

            repository.getAllTasks()
                .collect { taskList ->

                    Log.d(
                        "TaskMate",
                        "Received ${taskList.size} tasks"
                    )

                    taskList.forEach {
                        Log.d(
                            "TaskMate",
                            "${it.id} -> completed=${it.isCompleted}"
                        )
                    }

                    _tasks.value = taskList
                }
        }
    }

    fun toggleTaskCompleted(task: Task) {

        Log.d(
            "TaskMate",
            "Toggle -> id=${task.id}, completed=${task.isCompleted}, title=${task.title}"
        )

        viewModelScope.launch {

            val rows = repository.updateTaskCompletion(
                task.id,
                !task.isCompleted
            )

            Log.d("TaskMate", "Rows Updated = $rows")
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}