package com.gochoa.localizaciontest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gochoa.localizaciontest.domain.model.TaskStatus
import com.gochoa.localizaciontest.utils.Dictionary.TASK_TABLE

@Entity(tableName = TASK_TABLE)
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val title: String,
    val description: String,
    val date: String,
    var status: TaskStatus = TaskStatus.ToDo
)