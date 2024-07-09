package com.example.offlinetasktracker

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.offlinetasktracker.adapter.TaskAdapter
import com.example.offlinetasktracker.databinding.ActivityMainBinding
import com.example.offlinetasktracker.model.Task
import com.example.offlinetasktracker.util.putParcelableExtra
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


        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    taskAdapter.filter(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    taskAdapter.filter(it)
                }
                return true
            }
        })
    }

    private fun navigateToTaskActivity(task: Any?) {
        val intent = Intent(this, TaskActivity::class.java)
        task?.let {
            intent.putParcelableExtra(TaskActivity.EXTRA_TASK, it as Parcelable)
        }
        startActivity(intent)
    }
}














