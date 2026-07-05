package com.org.taskmate.viewmodel

import androidx.lifecycle.ViewModel
import com.org.taskmate.data.repository.TaskRepository

class HomeViewModel : ViewModel() {

    val tasks = TaskRepository.getTasks()
}