package com.example.persistenciadedatos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.persistenciadedatos.data.Task
import com.example.persistenciadedatos.repository.TaskRepository

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks = repository.allTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(title)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}