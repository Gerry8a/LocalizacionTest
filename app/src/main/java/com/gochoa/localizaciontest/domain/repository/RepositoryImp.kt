package com.gochoa.localizaciontest.domain.repository

import com.gochoa.localizaciontest.data.local.dao.TaskDao
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImp @Inject constructor(
    private val taskDao: TaskDao
) : Repository {

    override suspend fun insertTask(task: TaskEntity) = taskDao.insertTask(task)

    override suspend fun getAllTask(): Flow<MutableList<TaskEntity>> = taskDao.getAllTasks()
    override suspend fun updateStatus(entity: TaskEntity) {
      taskDao.updateStatus(entity)
    }

    override suspend fun deleteTask(entity: TaskEntity) {
       taskDao.deleteTask(entity)
    }

}