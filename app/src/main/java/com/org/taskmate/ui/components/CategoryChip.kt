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
import com.org.taskmate.data.enums.Category

@Composable
fun CategoryChip(
    category: Category
) {

    val chipColor = when (category) {
        Category.WORK -> Color(0xFF42A5F5)
        Category.PERSONAL -> Color(0xFFAB47BC)
        Category.SHOPPING -> Color(0xFF66BB6A)
        Category.BILLS -> Color(0xFFFFA726)
        else -> {
            MaterialTheme.colorScheme.primary
        }
    }

    Text(
        text = category.name.lowercase()
            .replaceFirstChar { it.uppercase() },
        color = Color.White,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier
            .background(
                chipColor,
                RoundedCornerShape(18.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 3.dp
            )
    )
}