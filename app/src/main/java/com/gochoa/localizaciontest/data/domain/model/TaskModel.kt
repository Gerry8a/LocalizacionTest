package com.gochoa.localizaciontest.data.domain.model

data class TaskModel (
    val title: String,
    val description: String,
    val date: String,
    var status: TaskStatus
)

