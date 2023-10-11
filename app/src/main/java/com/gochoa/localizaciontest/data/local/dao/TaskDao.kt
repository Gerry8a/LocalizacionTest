package com.gochoa.localizaciontest.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.gochoa.localizaciontest.data.local.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM TASK_TABLE")
    suspend fun getAllTasks(): MutableList<TaskEntity>

}