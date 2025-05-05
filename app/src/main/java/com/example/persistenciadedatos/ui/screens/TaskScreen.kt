package com.example.persistenciadedatos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.persistenciadedatos.ui.viewmodel.TaskViewModel
import com.example.persistenciadedatos.utils.UserPrefs
import com.example.persistenciadedatos.data.Task


@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val context = LocalContext.current
    val username = UserPrefs.getUsername(context) ?: "Usuario"
    val tasks by viewModel.tasks.collectAsState()

    var newTask by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("Tareas pendientes de $username", style = MaterialTheme.typography.h5)
        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("Nueva tarea") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (newTask.isNotBlank()) {
                    viewModel.addTask(newTask)
                    newTask = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Agregar")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(tasks, key = Task::id) { task ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(task.title)
                    IconButton(onClick = { viewModel.deleteTask(task) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }
    }
}
