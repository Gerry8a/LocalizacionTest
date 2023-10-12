package com.gochoa.localizaciontest.ui.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gochoa.localizaciontest.R
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.domain.model.TaskModel

class TaskAdapter(
    private var taskList: List<TaskEntity> = emptyList(),
    private val onItemSelected: (TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    fun updateList(list: List<TaskEntity>) {
        taskList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(taskList[position], onItemSelected)
    }

    private val differCallback = object : DiffUtil.ItemCallback<TaskEntity>(){
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem.taksId == newItem.taksId
        }

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}