package com.org.taskmate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardStats(
    total: Int,
    completed: Int,
    pending: Int,
    dueToday: Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Total",
            value = total
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Completed",
            value = completed
        )

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Pending",
            value = pending
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Due Today",
            value = dueToday
        )

    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: Int
) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = value.toString(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}