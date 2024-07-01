package com.example.offlinetasktracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(

    @PrimaryKey(autoGenerate = true)  public val id: Int = 0,
    var title: String,
    var description: String,
    val category: String,
    val dueDate: String,
    val priority: String,
    val tags: String,
    var isCompleted: String
)









