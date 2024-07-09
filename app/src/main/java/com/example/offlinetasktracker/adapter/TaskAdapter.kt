package com.example.offlinetasktracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinetasktracker.R
import com.example.offlinetasktracker.model.Task
import com.example.offlinetasktracker.viewmodel.TaskViewModel

class TaskAdapter(private val onClick: (Task) -> Unit) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    private var taskViewModel: TaskViewModel? = null
    private var originalList: List<Task> = emptyList()

    fun setViewModel(viewModel: TaskViewModel) {
        taskViewModel = viewModel
    }

    override fun submitList(list: List<Task>?) {
        super.submitList(list)
        originalList = list ?: emptyList()
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true) ||
                        it.tags.contains(query, ignoreCase = true)
            }
        }
        submitList(filteredList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        /*val decryptedTask = taskViewModel?.decryptTask(task)
        taskViewModel?.let { viewModel ->
            val decryptedTask = viewModel.decryptTask(task)
            holder.bind(decryptedTask, onClick)
        } ?: run {
            throw IllegalStateException("TaskViewModel has not been initialized")
        }*/
    // holder.bind(decryptedTask, onClick)
       holder.bind(task, onClick)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        private val taskDescription: TextView = itemView.findViewById(R.id.task_description)
        private val taskCategory: TextView = itemView.findViewById(R.id.task_category)
        private val taskPriority: TextView = itemView.findViewById(R.id.task_priority)
        private val taskTags: TextView = itemView.findViewById(R.id.task_tags)
        private val taskDueDate: TextView = itemView.findViewById(R.id.task_duedate)
        private val taskIsCompleted: TextView = itemView.findViewById(R.id.task_isCompleted)

        fun bind(task: Task, onClick: (Task) -> Unit) {
            taskTitle.text = task.title
            taskDescription.text = task.description
            taskCategory.text = task.category
            taskPriority.text = task.priority
            taskDueDate.text = task.dueDate
            taskTags.text = task.tags
            taskIsCompleted.text = task.isCompleted


            itemView.setOnClickListener {
                onClick(task)
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}







