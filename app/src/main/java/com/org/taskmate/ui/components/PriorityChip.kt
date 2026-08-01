package com.org.taskmate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.org.taskmate.data.enums.Priority

@Composable
fun PriorityChip(
    priority: Priority
) {

    val color = when (priority) {
        Priority.LOW -> Color(0xFF43A047)
        Priority.MEDIUM -> Color(0xFFFFC107)
        Priority.HIGH -> Color(0xFFE53935)
    }

    Text(
        text = priority.name.lowercase()
            .replaceFirstChar { it.uppercase() },
        color = Color.White,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier
            .background(
                color,
                RoundedCornerShape(18.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 3.dp
            )
    )
}