package com.gochoa.localizaciontest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gochoa.localizaciontest.data.local.dao.TaskDao
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.utils.Dictionary.DATABASE_VERSION

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}