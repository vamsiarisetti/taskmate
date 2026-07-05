package com.org.taskmate.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.taskmate.data.model.Task
import com.org.taskmate.ui.components.TaskCard
import com.org.taskmate.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onAddTaskClick: () -> Unit
) {
    val homeViewModel: HomeViewModel = viewModel()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTaskClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Task"
                )
            }
        }
    ) { innerPadding ->

        HomeContent(
            paddingValues = innerPadding,
            tasks = homeViewModel.tasks
        )

    }
}

@Composable
private fun HomeContent(
    paddingValues: PaddingValues,
    tasks: List<Task>
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
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskCard(task = task)
            }
        }
    }
}