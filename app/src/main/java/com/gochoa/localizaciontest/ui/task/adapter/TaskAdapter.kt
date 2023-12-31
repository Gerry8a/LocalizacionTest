package com.gochoa.localizaciontest.ui.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gochoa.localizaciontest.R
import com.gochoa.localizaciontest.data.local.entity.TaskEntity

class TaskAdapter(
    private var taskList: List<TaskEntity> = emptyList(),
    private val onItemSelected: (TaskEntity) -> Unit,
    private val onLongClickListener: (TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(taskList[position], onItemSelected, onLongClickListener)
    }


}