package com.example.offlinetasktracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.offlinetasktracker.databinding.ActivityTaskBinding
import com.example.offlinetasktracker.model.Task
import com.example.offlinetasktracker.viewmodel.TaskViewModel

class TaskActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TASK = "com.example.offlinetasktracker.EXTRA_TASK"
    }

    private lateinit var binding: ActivityTaskBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // task = intent.getParcelableExtra(EXTRA_TASK)

      //  task?.let {
          //  binding.editTextTitle.setText("Test")
          //  binding.editTextDescription.setText("Testing")
            // Initialize other fields like category, due date, and priority
       // }

        binding.buttonSaveTask.setOnClickListener {
            saveTask()
        }
    }

    private fun saveTask() {
        val title = binding.editTextTitle.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        val dueDate = binding.editTextDueDate.text.toString().trim()
        val category = binding.editTextCategory.text.toString().trim()
        val priority = binding.editTextPriority.toString().trim()
        val isCompleted = binding.completedCheckbox.text.toString().trim()
        val tags = binding.editTextTags.text.toString().trim()

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please enter a title and description", Toast.LENGTH_SHORT).show()
            return
        }

        if (dueDate.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please enter a dueDate and category", Toast.LENGTH_SHORT).show()
            return
        }

        if (priority.isEmpty() || isCompleted.isEmpty() ) {
            Toast.makeText(this, "Please enter a priority and isCompleted", Toast.LENGTH_SHORT).show()
            return
        }

        if (task == null) {
            task = Task(
                title = title,
                description = description,
                category = category,
                dueDate = dueDate,
                priority = priority,
                tags = tags,
                isCompleted = isCompleted
            )
            taskViewModel.insertTask(task!!)
        } else {
            task?.apply {
                this.title = title
                this.description = description
                // Update other fields like category, due date, and priority
            }
            taskViewModel.updateTask(task!!)
        }

        finish()
    }
}


