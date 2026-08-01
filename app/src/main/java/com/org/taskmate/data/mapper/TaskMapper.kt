package com.org.taskmate.data.mapper

import com.org.taskmate.data.entity.TaskEntity
import com.org.taskmate.data.model.Task

fun TaskEntity.toTask(): Task {

    return Task(
        id = id,
        title = title,
        description = description,
        category = category,
        priority = priority,
        dueDate = dueDate,
        reminderTime = reminderTime,
        isCompleted = isCompleted
    )
}

fun Task.toEntity(): TaskEntity {

    return TaskEntity(
        id = id,
        title = title,
        description = description,
        category = category,
        priority = priority,
        dueDate = dueDate,
        reminderTime = reminderTime,
        isCompleted = isCompleted
    )
}