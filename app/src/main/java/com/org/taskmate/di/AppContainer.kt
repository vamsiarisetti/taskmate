package com.org.taskmate.di

import android.content.Context
import com.org.taskmate.data.database.DatabaseProvider
import com.org.taskmate.data.repository.TaskRepository

object AppContainer {

    private var repository: TaskRepository? = null

    fun repository(context: Context): TaskRepository {

        return repository ?: run {

            val database = DatabaseProvider.getDatabase(context)

            val repo = TaskRepository(
                database.taskDao()
            )

            repository = repo

            repo
        }
    }
}