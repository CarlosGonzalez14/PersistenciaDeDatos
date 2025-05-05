package com.example.persistenciadedatos.repository

import com.example.persistenciadedatos.data.TaskDao
import com.example.persistenciadedatos.data.Task

class TaskRepository(private val dao: TaskDao) {
    val allTasks = dao.getAll()

    suspend fun addTask(title: String) {
        dao.insert(Task(title = title))
    }

    suspend fun deleteTask(task: Task) {
        dao.delete(task)
    }
}