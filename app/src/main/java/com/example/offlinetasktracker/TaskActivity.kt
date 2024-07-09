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

        task = intent.getParcelableExtra(EXTRA_TASK)

       task?.let {
           binding.editTextTitle.setText(it.title)
            binding.editTextDescription.setText(it.description)
            binding.editTextCategory.setText(it.category)
            binding.editTextDueDate.setText(it.dueDate)
            binding.editTextPriority.setText(it.priority)
            binding.completedCheckbox.setText(it.isCompleted)
            binding.editTextTags.setText(it.tags)
        }

        binding.buttonSaveTask.setOnClickListener {
            saveTask()
        }

        binding.buttonDeleteTask.setOnClickListener {
            deleteTask(task)
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
                this.category = category
                this.isCompleted = isCompleted
                this.dueDate = dueDate
                this.priority = priority
                this.tags = tags
            }
            taskViewModel.updateTask(task!!)
        }

        finish()
    }
    private fun deleteTask(task: Task?) {
        if (task != null) {
            taskViewModel.deleteTask(task!!)
        }
        finish()
    }
}


