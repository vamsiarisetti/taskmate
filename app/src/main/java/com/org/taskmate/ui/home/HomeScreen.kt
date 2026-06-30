package com.org.taskmate.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.org.taskmate.ui.components.TaskCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {

        Text(
            text = "TaskMate",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Good Morning, Vamsi 👋",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        Text(
            text = "Today's Tasks",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(HomeViewModel.tasks) { task ->
                TaskCard(task)
            }
        }
    }
}