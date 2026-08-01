package com.org.taskmate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TimePickerField(
    time: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {

        OutlinedTextField(
            value = time,
            onValueChange = {},
            enabled = false,
            label = {
                Text("Reminder Time")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}