package com.gochoa.localizaciontest.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gochoa.localizaciontest.data.local.UIState
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.domain.repository.RepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repositoryImp: RepositoryImp
): ViewModel() {

    private val _taskList = MutableLiveData<UIState<MutableList<TaskEntity>>>()
    val taskList: LiveData<UIState<MutableList<TaskEntity>>> get() = _taskList

    init {
        val taskEntity = TaskEntity(
            status = "asd",
            description = "asd",
            date = "asd",
            title = "sdsf"
        )
//        insertTask(taskEntity)
        getTasks()
    }

    fun getTasks() = viewModelScope.launch {
        repositoryImp.getAllTask().let {
            if (it.isNotEmpty()){
              _taskList.value = UIState.Success(it)
            } else {
                _taskList.value = UIState.Error("dsfds")
            }
        }
    }

    fun insertTask(task: TaskEntity) = viewModelScope.launch{

        repositoryImp.insertTask(task)
    }


}
