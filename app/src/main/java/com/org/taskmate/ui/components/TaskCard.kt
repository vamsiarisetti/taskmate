package com.org.taskmate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.org.taskmate.data.model.Task
import androidx.compose.material.icons.outlined.Delete

@Composable
fun TaskCard(
    task: Task,
    onCheckedChange: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .alpha(if (task.isCompleted) 0.6f else 1f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            IconButton(
                onClick = onCheckedChange
            ) {
                Icon(
                    imageVector =
                        if (task.isCompleted)
                            Icons.Outlined.CheckBox
                        else
                            Icons.Outlined.CheckBoxOutlineBlank,
                    contentDescription = "Toggle Task"
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration =
                        if (task.isCompleted)
                            TextDecoration.LineThrough
                        else
                            TextDecoration.None
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    CategoryChip(task.category)

                    PriorityChip(task.priority)

                }

                if (!task.dueDate.isNullOrBlank()) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "📅 ${task.dueDate}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            IconButton(
                onClick = onEditClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Task"
                )
            }

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}