package com.org.taskmate.data.repository

import com.org.taskmate.data.dao.TaskDao
import com.org.taskmate.data.mapper.toEntity
import com.org.taskmate.data.mapper.toTask
import com.org.taskmate.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(
    private val taskDao: TaskDao
) {

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { entities ->
            entities.map { it.toTask() }
        }
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task.toEntity())
    }

    suspend fun update(task: Task) {
        taskDao.update(task.toEntity())
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task.toEntity())
    }

    suspend fun updateTaskCompletion(
        taskId: Long,
        completed: Boolean
    ): Int {
        return taskDao.updateTaskCompletion(taskId, completed)
    }

    suspend fun getTaskById(taskId: Long): Task? {
        return taskDao.getTaskById(taskId)?.toTask()
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task.toEntity())
    }
}