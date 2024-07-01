package com.example.offlinetasktracker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.offlinetasktracker.database.TaskDao
import com.example.offlinetasktracker.database.TaskDatabase
import com.example.offlinetasktracker.model.Task
import net.sqlcipher.database.SQLiteDatabase

class TaskRepository(application: Application) {

    private val taskDao: TaskDao
    private val allTasks: LiveData<List<Task>>

    init {
        val passphrase: CharArray = SQLiteDatabase.getChars("your-secure-passphrase".toByteArray())
        val database = TaskDatabase.getDatabase(application, passphrase)
       // val database = TaskDatabase.getDatabase(application)
        taskDao = database.taskDao()
        allTasks = taskDao.getAllTasks()
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return allTasks
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    fun getTasksByCategory(category: String): LiveData<List<Task>> {
        return taskDao.getTasksByCategory(category)
    }

    fun searchTasks(query: String): LiveData<List<Task>> {
        return taskDao.searchTasks(query)
    }
}







