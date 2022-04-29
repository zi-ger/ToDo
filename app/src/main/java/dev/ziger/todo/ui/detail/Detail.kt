package dev.ziger.todo.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ziger.todo.data.Todo

@Composable
fun DetailScreen(
    selectedId: Long,
    onNavigate: () -> Unit
) {
    val viewModel = viewModel(
        DetailViewModel::class.java,
        factory = DetailViewModelFactory(selectedId)
    )

    val state by viewModel.state.collectAsState()

    DetailScreenComponent(
        todoText = state.todo,
        onTodoTextChange = { viewModel.onTextChange(it) },
        timeText = state.time,
        onTimeTextChange = { viewModel.onTimeChange(it) },
        onNavigate = { onNavigate() },
        onSaveTodo = { viewModel.insert(it) },
        selectedId = state.selectId
    )
}

@Composable
fun DetailScreenComponent(
    todoText: String,
    onTodoTextChange: (String) -> Unit,
    timeText: String,
    onTimeTextChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTodo: (Todo) -> Unit,
    selectedId: Long
) {
    // if -1L == not editing
    val isTodoEdit = selectedId == -1L

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            todoText,
            onValueChange = { onTodoTextChange(it) },
            label = { Text("Enter Todo") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            timeText,
            onValueChange = { onTimeTextChange(it) },
            label = { Text("Enter time") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button( {
            val todo = if (isTodoEdit) {
                Todo(todoText, timeText, false)
            } else {
                Todo(todoText, timeText, false, selectedId)
            }

            onSaveTodo(todo)
            onNavigate()
        } ) {
            val saveText = if (isTodoEdit) "Save Todo" else "Update Todo"
            Text(saveText)
        }
    }
}