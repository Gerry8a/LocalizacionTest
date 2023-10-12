package com.gochoa.localizaciontest.domain.repository

import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.domain.model.TaskModel

interface Repository {
    suspend fun insertTask(task: TaskEntity)
    suspend fun getAllTask(): MutableList<TaskEntity>
}