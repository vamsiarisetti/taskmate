package com.org.taskmate.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.org.taskmate.data.enums.Category
import com.org.taskmate.data.enums.Priority

@Entity(tableName = "tasks")
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,

    val description: String,

    val category: Category,

    val priority: Priority,

    val dueDate: String?,

    val reminderTime: String?,

    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean = false
)