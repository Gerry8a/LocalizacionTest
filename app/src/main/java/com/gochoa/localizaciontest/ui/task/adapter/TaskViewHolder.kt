package com.gochoa.localizaciontest.ui.task.adapter

import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gochoa.localizaciontest.R
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.domain.model.TaskModel
import com.gochoa.localizaciontest.databinding.ItemTaskBinding

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTaskBinding.bind(view)

    fun render(
        task: TaskEntity,
        onItemSelected: (TaskEntity) -> Unit,
        onLongClickListener: (TaskEntity) -> Unit
    ) {
        val context = binding.tvContent.context

        binding.tvContent.text = task.description
        binding.tvDate.text = task.date
        binding.tvTitle.text = task.title
        binding.parent.setOnClickListener {
            onItemSelected(task)
        }

        binding.parent.setOnLongClickListener {
            onLongClickListener(task)
            true
        }


        binding.tvStatus.text = task.status

        when (task.status) {
            "Por hacer" -> {
                binding.cvTask.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.md_theme_light_inversePrimary
                    )
                )
            }

            "En progreso" -> {
                binding.cvTask.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.progress
                    )
                )
            }

            "Terminada" -> {
                binding.cvTask.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.disable
                    )
                )
            }
        }
    }
}