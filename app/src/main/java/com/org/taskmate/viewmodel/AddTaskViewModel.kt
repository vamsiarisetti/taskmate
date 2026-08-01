package com.org.taskmate.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.org.taskmate.data.enums.Category
import com.org.taskmate.data.enums.Priority
import com.org.taskmate.data.model.Task
import com.org.taskmate.data.repository.TaskRepository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.org.taskmate.di.AppContainer
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

//    private val repository =
//        AppContainer.repository(application)
    private var editingTaskId: Long? = null
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

        viewModelScope.launch {

            repository.insert(
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

    fun loadTask(taskId: Long) {

        viewModelScope.launch {

            repository.getTaskById(taskId)?.let { task ->

                editingTaskId = task.id

                title = task.title
                description = task.description
                category = task.category
                priority = task.priority
                dueDate = task.dueDate ?: ""
                reminderTime = task.reminderTime ?: ""
            }
        }
    }

    fun updateTask() {

        val id = editingTaskId ?: return

        viewModelScope.launch {

            repository.update(

                Task(
                    id = id,
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
}