package com.example.offlinetasktracker

import android.app.Application
import androidx.room.Room
import com.example.offlinetasktracker.database.TaskDatabase
import com.example.offlinetasktracker.repository.TaskRepository

class TaskApplication : Application() {
    private val database by lazy {
        Room.databaseBuilder(
            this,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    val repository by lazy {
      //  TaskRepository(database.taskDao())
    }
}


