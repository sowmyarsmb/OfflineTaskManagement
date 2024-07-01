package com.example.offlinetasktracker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.offlinetasktracker.model.Task

@Dao
interface TaskDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertTask(task: Task)

        @Update
        suspend fun updateTask(task: Task)

        @Delete
        suspend fun deleteTask(task: Task)

        @Query("SELECT * FROM task_table ORDER BY priority DESC")
        fun getAllTasks(): LiveData<List<Task>>

        @Query("SELECT * FROM task_table WHERE category = :category ORDER BY priority DESC")
        fun getTasksByCategory(category: String): LiveData<List<Task>>

        @Query("SELECT * FROM task_table WHERE title LIKE :query OR description LIKE :query OR category LIKE :query ORDER BY priority DESC")
        fun searchTasks(query: String): LiveData<List<Task>>
}






