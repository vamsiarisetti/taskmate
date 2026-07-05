package com.org.taskmate.ui.addtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.taskmate.ui.components.AppButton
import com.org.taskmate.ui.components.AppTextField
import com.org.taskmate.ui.components.AppTopBar
import com.org.taskmate.ui.components.CategoryDropdown
import com.org.taskmate.ui.components.DatePickerField
import com.org.taskmate.ui.components.PrioritySelector
import com.org.taskmate.ui.components.TimePickerField
import com.org.taskmate.viewmodel.AddTaskViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onBack: () -> Unit
) {

    val viewModel: AddTaskViewModel = viewModel()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Add Task",
                showBackButton = true,
                onBackClick = onBack
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AppTextField(
                value = viewModel.title,
                label = "Task Title",
                onValueChange = {
                    viewModel.updateTitle(it)
                }
            )

            AppTextField(
                value = viewModel.description,
                label = "Description",
                onValueChange = {
                    viewModel.updateDescription(it)
                },
                singleLine = false,
                minLines = 3,
                maxLines = 5
            )

            CategoryDropdown(
                selectedCategory = viewModel.category,
                onCategorySelected = {
                    viewModel.updateCategory(it)
                }
            )

            PrioritySelector(
                selectedPriority = viewModel.priority,
                onPrioritySelected = {
                    viewModel.updatePriority(it)
                }
            )

            DatePickerField(
                date = viewModel.dueDate,
                onClick = {

                    DatePickerDialog(
                        context,
                        { _, year, month, day ->

                            viewModel.updateDueDate(
                                "%02d/%02d/%04d".format(
                                    day,
                                    month + 1,
                                    year
                                )
                            )

                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()

                }
            )

            TimePickerField(
                time = viewModel.reminderTime,
                onClick = {

                    TimePickerDialog(
                        context,
                        { _, hour, minute ->

                            viewModel.updateReminderTime(
                                "%02d:%02d".format(
                                    hour,
                                    minute
                                )
                            )

                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false
                    ).show()

                }
            )

            AppButton(
                text = "Save Task",
                enabled = viewModel.title.isNotBlank(),
                onClick = {
                    viewModel.saveTask()
                    onBack()
                }
            )

        }

    }

}