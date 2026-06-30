package com.org.taskmate.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.org.taskmate.model.Task

@Composable
fun TaskCard(
    task: Task
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Icon(
                imageVector = Icons.Outlined.CheckBoxOutlineBlank,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = task.category,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = task.due,
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

    }

}