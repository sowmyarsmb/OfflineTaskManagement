package com.example.offlinetasktracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.offlinetasktracker.adapter.TaskAdapter
import com.example.offlinetasktracker.databinding.ActivityMainBinding
import com.example.offlinetasktracker.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskAdapter = TaskAdapter { task -> navigateToTaskActivity(task) }
        binding.recyclerView.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.fabAddTask.setOnClickListener {
            navigateToTaskActivity(null)
        }

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                taskAdapter.submitList(it)
            }
        })
    }

    private fun navigateToTaskActivity(task: Any?) {
        val intent = Intent(this, TaskActivity::class.java)
        /*task?.let {
            intent.putExtra(TaskActivity.EXTRA_TASK, it)
        }*/
        startActivity(intent)
    }
}













