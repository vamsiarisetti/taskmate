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
import com.org.taskmate.data.model.DashboardState
import com.org.taskmate.data.repository.TaskRepository

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.org.taskmate.data.enums.SortType

class HomeViewModel(
    private val repository: TaskRepository
) : ViewModel() {

//    private val repository =
//        AppContainer.repository(application)

    private var allTasks: List<Task> = emptyList()
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private val _sortType = MutableStateFlow(SortType.NEWEST)
    val sortType: StateFlow<SortType> = _sortType

    private val _dashboard = MutableStateFlow(DashboardState())
    val dashboard: StateFlow<DashboardState> = _dashboard

    private val _tasks =
        MutableStateFlow<List<Task>>(emptyList())

    val tasks: StateFlow<List<Task>> =
        _tasks.asStateFlow()

    val totalTasks: Int
        get() = allTasks.size

    val completedTasks: Int
        get() = allTasks.count { it.isCompleted }

    val pendingTasks: Int
        get() = allTasks.count { !it.isCompleted }

    val dueTodayTasks: Int
        get() {
            val today = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date())

            return allTasks.count {
                it.dueDate == today
            }
        }

    init {
        loadTasks()
    }

    private fun loadTasks() {

        viewModelScope.launch {

            repository.getAllTasks().collect { taskList ->
                allTasks = taskList
                Log.d(
                    "TaskMate",
                    "Received ${taskList.size} tasks"
                )
                filterTasks()
                /*val filtered = allTasks.filter { task ->

                    val matchesSearch =
                        task.title.contains(_searchText.value, ignoreCase = true) ||
                        task.description.contains(_searchText.value, ignoreCase = true)
                    val matchesCategory =
                        _selectedCategory.value == "All" ||
                        task.category.name.equals(_selectedCategory.value,ignoreCase = true)
                    matchesSearch && matchesCategory
                }
                _tasks.value = filtered*/
            }
        }
    }

    fun updateSearchText(text: String) {
        _searchText.value = text
        filterTasks()
    }

    fun updateSort(sortType: SortType) {
        _sortType.value = sortType
        filterTasks()
    }

    private fun filterTasks() {

        val query = _searchText.value.trim()
        val category = _selectedCategory.value

        val filteredTasks = allTasks.filter { task ->

            val matchesSearch =
                query.isBlank() ||
                        task.title.contains(query, ignoreCase = true) ||
                        task.description.contains(query, ignoreCase = true)

            val matchesCategory =
                category == "All" ||
                        task.category.name.equals(category, ignoreCase = true)

            matchesSearch && matchesCategory
        }

        _tasks.value = when (_sortType.value) {

            SortType.NEWEST ->
                filteredTasks.sortedByDescending { it.id }

            SortType.OLDEST ->
                filteredTasks.sortedBy { it.id }

            SortType.DUE_DATE ->
                filteredTasks.sortedBy { it.dueDate }

            SortType.PRIORITY ->
                filteredTasks.sortedByDescending { it.priority.ordinal }
        }

        val today = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Date())
        Log.d("TaskMate", "Today's Date = $today")
        allTasks.forEach {
            Log.d(
                "TaskMate",
                "Task=${it.title}, Due=${it.dueDate}"
            )
        }
        _dashboard.value = DashboardState(
            total = allTasks.size,
            completed = allTasks.count { it.isCompleted },
            pending = allTasks.count { !it.isCompleted },
            dueToday = allTasks.count {
                !it.isCompleted &&
                it.dueDate == today
            }
        )
    }

    fun updateCategory(category: String) {
        _selectedCategory.value = category
        filterTasks()
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