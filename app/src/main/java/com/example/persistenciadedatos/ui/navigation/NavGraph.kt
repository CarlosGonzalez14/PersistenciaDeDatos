package com.example.persistenciadedatos.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Tasks : Screen("tasks")
}