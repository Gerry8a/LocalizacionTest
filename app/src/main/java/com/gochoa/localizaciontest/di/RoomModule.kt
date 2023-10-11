package com.gochoa.localizaciontest.di

import android.content.Context
import androidx.room.Room
import com.gochoa.localizaciontest.data.local.TaskDatabase
import com.gochoa.localizaciontest.utils.Dictionary.TASK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, TaskDatabase::class.java, TASK_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.getTaskDao()

}