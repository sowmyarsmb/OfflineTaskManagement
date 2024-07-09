package com.example.offlinetasktracker.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class Task(

    @PrimaryKey(autoGenerate = true)  public val id: Int = 0,
    var title: String,
    var description: String,
    var category: String,
    var dueDate: String,
    var priority: String,
    var tags: String,
    var isCompleted: String
) : Parcelable









