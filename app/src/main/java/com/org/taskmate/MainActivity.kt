package com.org.taskmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.org.taskmate.ui.navigation.AppNavigation
import com.org.taskmate.ui.theme.TaskMateTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TaskMateTheme {
                AppNavigation()
            }
        }
    }
}