package com.org.taskmate.data.database

import androidx.room.TypeConverter
import com.org.taskmate.data.enums.Category
import com.org.taskmate.data.enums.Priority

class Converters {

    @TypeConverter
    fun fromCategory(category: Category): String =
        category.name

    @TypeConverter
    fun toCategory(value: String): Category =
        Category.valueOf(value)

    @TypeConverter
    fun fromPriority(priority: Priority): String =
        priority.name

    @TypeConverter
    fun toPriority(value: String): Priority =
        Priority.valueOf(value)
}