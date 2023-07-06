package com.example.firebasesample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesample.databinding.ActivityMainBinding
import com.example.firebasesample.databinding.TaskListItemBinding
import java.text.SimpleDateFormat
import java.util.*


class TaskAdapter:RecyclerView.Adapter<TaskViewHolder>()  {
    private val taskList: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.titleTextView.text = task.title
    }
    override fun getItemCount(): Int = taskList.size

    fun updateTasks(newList: List<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }

}
