package com.example.offlinetasktracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.offlinetasktracker.database.TaskDatabase
import com.example.offlinetasktracker.model.Task
import com.example.offlinetasktracker.repository.TaskRepository
import com.example.offlinetasktracker.util.EncryptionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.crypto.SecretKey

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>
    private val encryptionKey: SecretKey = EncryptionUtil.generateKey()

    init {
      //  val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(application)
        allTasks = repository.getAllTasks()
    }

    /*fun insertTask(task: Task) {
        val encryptedTitle = EncryptionUtil.encrypt(task.title, encryptionKey)
        val encryptedDescription = EncryptionUtil.encrypt(task.description, encryptionKey)
        val encryptedTask = task.copy(title = encryptedTitle, description = encryptedDescription)
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTask(encryptedTask)
        }
    }*/

    fun decryptTask(task: Task): Task {
        val decryptedTitle = EncryptionUtil.decrypt(task.title, encryptionKey)
        val decryptedDescription = EncryptionUtil.decrypt(task.description, encryptionKey)
        return task.copy(title = decryptedTitle, description = decryptedDescription)
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    fun markTaskCompleted(task: Task) = viewModelScope.launch {
        task.isCompleted = true.toString()
        repository.updateTask(task)
    }

    fun getTasksByCategory(category: String): LiveData<List<Task>> {
        return repository.getTasksByCategory(category)
    }

    fun searchTasks(query: String): LiveData<List<Task>> {
        return repository.searchTasks(query)
    }
}









