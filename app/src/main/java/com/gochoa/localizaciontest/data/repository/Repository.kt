package com.gochoa.localizaciontest.data.repository

import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertTask(task: TaskEntity)
    suspend fun getAllTask(): Flow<MutableList<TaskEntity>>
    suspend fun updateStatus(entity: TaskEntity)
    suspend fun deleteTask(entity: TaskEntity)
}