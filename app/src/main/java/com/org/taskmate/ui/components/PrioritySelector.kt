package com.org.taskmate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.org.taskmate.data.enums.Priority

@Composable
fun PrioritySelector(
    selectedPriority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Priority.entries.forEach { priority ->

            FilterChip(
                selected = priority == selectedPriority,
                onClick = {
                    onPrioritySelected(priority)
                },
                label = {
                    Text(priority.displayName)
                }
            )

        }

    }

}