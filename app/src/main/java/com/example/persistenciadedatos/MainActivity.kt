package com.example.persistenciadedatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.persistenciadedatos.data.AppDatabase
import com.example.persistenciadedatos.ui.viewmodel.TaskViewModel
import com.example.persistenciadedatos.repository.TaskRepository
import com.example.persistenciadedatos.ui.navigation.Screen
import com.example.persistenciadedatos.ui.screens.TaskScreen
import com.example.persistenciadedatos.ui.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersistenciaDeDatosApp()
        }
    }
}

@Composable
fun PersistenciaDeDatosApp() {
    val navController = rememberNavController()
    val db = AppDatabase.getDatabase(LocalContext.current)
    val viewModel = TaskViewModel(TaskRepository(db.taskDao()))
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController, startDestination = Screen.Login.route) {
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Tasks.route) {
                TaskScreen(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PersistenciaDeDatosApp()
}
