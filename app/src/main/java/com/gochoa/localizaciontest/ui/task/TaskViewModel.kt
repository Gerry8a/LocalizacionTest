package com.gochoa.localizaciontest.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gochoa.localizaciontest.data.local.UIState
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.domain.repository.RepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repositoryImp: RepositoryImp
) : ViewModel() {

    private val _taskList = MutableLiveData<UIState<MutableList<TaskEntity>>>()
    val taskList: LiveData<UIState<MutableList<TaskEntity>>> get() = _taskList

    init {
        getTask()
    }

    fun getTask() = viewModelScope.launch {
        repositoryImp.getAllTask()
            .catch {
                _taskList.postValue(UIState.Error(""))
            }
            .collect { _taskList.postValue(UIState.Success(it)) }
    }

    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        repositoryImp.insertTask(task)
    }

    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        when (task.status) {
            "Por hacer" -> {
                task.status = "En progreso"
            }
            "En progreso" -> {
                task.status = "Terminada"
            }
            "Terminada" -> {
                task.status = "En progreso"
            }
        }
        repositoryImp.updateStatus(task)
    }
}
