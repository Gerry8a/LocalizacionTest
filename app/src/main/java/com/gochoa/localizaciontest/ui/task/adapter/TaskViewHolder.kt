package com.gochoa.localizaciontest.ui.task.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gochoa.localizaciontest.domain.model.TaskModel
import com.gochoa.localizaciontest.databinding.ItemTaskBinding

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val binding = ItemTaskBinding.bind(view)

    fun render(task: TaskModel, onItemSelected: (TaskModel) -> Unit){
        val context = binding.tvContent.context

        binding.tvContent.text = task.description
        binding.tvDate.text = task.date
        binding.tvTitle.text = task.title
    }
}