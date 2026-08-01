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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.taskmate.data.model.Task
import com.org.taskmate.di.AppContainer
import com.org.taskmate.ui.components.TaskCard
import com.org.taskmate.viewmodel.HomeViewModel
import com.org.taskmate.viewmodel.TaskViewModelFactory

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateOf
import com.org.taskmate.data.enums.SortType
import com.org.taskmate.ui.components.DashboardStats

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextButton

@Composable
fun HomeScreen(
    onAddTaskClick: () -> Unit,
    onEditTaskClick: (Long) -> Unit
) {

    val context = LocalContext.current

    val factory = remember {
        TaskViewModelFactory(
            AppContainer.repository(context)
        )
    }

    val homeViewModel: HomeViewModel = viewModel(
        factory = factory
    )

    val tasks by homeViewModel.tasks.collectAsState()
    val dashboard by homeViewModel.dashboard.collectAsState()
    /*val sortType by homeViewModel.sortType.collectAsState()
    var expanded by remember { mutableStateOf(false) }*/

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTaskClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        }
    ) { innerPadding ->

        HomeContent(
            paddingValues = innerPadding,
            tasks = tasks,

            totalTasks = dashboard.total,
            completedTasks = dashboard.completed,
            pendingTasks = dashboard.pending,
            dueTodayTasks = dashboard.dueToday,

            searchText = homeViewModel.searchText.collectAsState().value,
            selectedCategory = homeViewModel.selectedCategory.collectAsState().value,

            sortType = homeViewModel.sortType.collectAsState().value,
            onSortChanged = {
                homeViewModel.updateSort(it)
            },
            onCategorySelected = {
                homeViewModel.updateCategory(it)
            },
            onSearchChange = {
                homeViewModel.updateSearchText(it)
            },
            onTaskChecked = { task ->
                homeViewModel.toggleTaskCompleted(task)
            },
            onEditTask = { task ->

                // Sprint 4.3
                // We'll navigate to Edit Task screen here.
                onEditTaskClick(task.id)
            },
            onDeleteTask = { task ->
                homeViewModel.deleteTask(task)
            }
        )
    }
}

@Composable
private fun HomeContent(
    paddingValues: PaddingValues,
    tasks: List<Task>,

    totalTasks: Int,
    completedTasks: Int,
    pendingTasks: Int,
    dueTodayTasks: Int,

    sortType: SortType,
    onSortChanged: (SortType) -> Unit,

    searchText: String,
    onSearchChange: (String) -> Unit,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onTaskChecked: (Task) -> Unit,
    onEditTask: (Task) -> Unit,
    onDeleteTask: (Task) -> Unit
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
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 16.dp
            )
        )

        DashboardStats(
            total = totalTasks,
            completed = completedTasks,
            pending = pendingTasks,
            dueToday = dueTodayTasks
        )

        Spacer(modifier = Modifier.height(20.dp))

//        Spacer(modifier = Modifier.height(20.dp))

//      Search bar
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text("Search tasks...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(12.dp))

//      Categories filter
        val categories = listOf(
            "All",
            "WORK",
            "PERSONAL",
            "SHOPPING",
            "HEALTH",
            "BILLS"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            categories.forEach { category ->

                FilterChip(
                    selected = selectedCategory.equals(category, true),
                    onClick = {
                        onCategorySelected(category)
                    },
                    label = {
                        Text(category.replaceFirstChar { it.uppercase() })
                    }
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        /*Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Today's Tasks",
                style = MaterialTheme.typography.titleLarge
            )

            var expanded by remember { mutableStateOf(false) }

            Box {

                TextButton(
                    onClick = { expanded = true }
                ) {
                    Text(sortType.name.replace("_", " "))
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {

                    SortType.entries.forEach { sort ->

                        DropdownMenuItem(
                            text = {
                                Text(sort.name.replace("_", " "))
                            },
                            onClick = {
                                onSortChanged(sort)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))*/

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Today's Tasks",
                style = MaterialTheme.typography.titleLarge
            )

            var expanded by remember { mutableStateOf(false) }

            Box {

                TextButton(
                    onClick = { expanded = true }
                ) {
                    Text(sortType.name.replace("_", " "))
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {

                    SortType.entries.forEach { sort ->

                        DropdownMenuItem(
                            text = {
                                Text(sort.name.replace("_", " "))
                            },
                            onClick = {
                                onSortChanged(sort)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(tasks) { task ->

                TaskCard(
                    task = task,
                    onCheckedChange = {
                        onTaskChecked(task)
                    },
                    onEditClick = {
                        onEditTask(task)
                    },
                    onDeleteClick = {
                        onDeleteTask(task)
                    }
                )
            }
        }
    }
}