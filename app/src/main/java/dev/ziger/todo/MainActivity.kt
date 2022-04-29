package dev.ziger.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import dev.ziger.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    TopAppBar(title = {
                        Text("Todo App")
                    })
                    TodoNavHost()
                }
            }
        }
    }
}