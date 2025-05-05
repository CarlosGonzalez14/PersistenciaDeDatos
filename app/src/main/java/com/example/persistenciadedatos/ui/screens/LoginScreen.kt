package com.example.persistenciadedatos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.persistenciadedatos.ui.navigation.Screen
import com.example.persistenciadedatos.utils.UserPrefs
import androidx.compose.ui.platform.LocalContext

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("Ingresa tu nombre de usuario", style = MaterialTheme.typography.h6)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                if (name.isNotBlank()) {
                    UserPrefs.saveUsername(context, name)
                    navController.navigate(Screen.Tasks.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
        }
    }
}
