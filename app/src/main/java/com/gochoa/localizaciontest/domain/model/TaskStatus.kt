package com.gochoa.localizaciontest.domain.model

sealed class TaskStatus (){
    object ToDo : TaskStatus()
    object InProgress : TaskStatus()
    object Finished : TaskStatus()
}