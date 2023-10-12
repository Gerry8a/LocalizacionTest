package com.gochoa.localizaciontest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM TASK_TABLE")
    fun getAllTasks(): Flow<MutableList<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

//    @Query("UPDATE TASK_TABLE SET taskId = :id")
//    suspend fun updateWorkCenter(id: Int, centroTrabajoAct: String)

}