package com.example.firebasesample

import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesample.databinding.TaskListItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskViewHolder(val binding: TaskListItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task){
        binding.titleTextView.text = task.title
        binding.dateTextView.text =
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(task.createdAt)
    }
}
