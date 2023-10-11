package com.gochoa.localizaciontest.data.domain.model

sealed class TaskStatus (){
    object ToDo : TaskStatus()
    object InProgress : TaskStatus()
    object Finished : TaskStatus()
}