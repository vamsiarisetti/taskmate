package com.org.taskmate.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.org.taskmate.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("""
        SELECT * FROM tasks
        ORDER BY isCompleted ASC, id DESC
    """)
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun insert(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("UPDATE tasks SET isCompleted = :completed WHERE id = :taskId")
    suspend fun updateTaskCompletion(
        taskId: Long,
        completed: Boolean
    ): Int

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Long): TaskEntity?
}