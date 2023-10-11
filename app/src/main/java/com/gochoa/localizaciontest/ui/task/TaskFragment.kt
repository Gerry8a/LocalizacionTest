package com.gochoa.localizaciontest.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gochoa.localizaciontest.R
import com.gochoa.localizaciontest.data.domain.model.TaskModel
import com.gochoa.localizaciontest.data.domain.model.TaskStatus
import com.gochoa.localizaciontest.databinding.FragmentTaskBinding
import com.gochoa.localizaciontest.ui.task.adapter.TaskAdapter


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskModel = TaskModel(title = "Empezar test", date = "Creado el 10/10/2023", description = "Texto de prueba", status = TaskStatus.ToDo)
        val ttt = TaskModel(title = "Empezar test", date = "Creado el 10/10/2023", description = "Texto de prueba", status = TaskStatus.ToDo)
        val ggg = TaskModel(title = "Empezar test", date = "Creado el 10/10/2023", description = "Texto de prueba", status = TaskStatus.ToDo)
        val lista = arrayListOf(taskModel, ttt, ggg)

        taskAdapter = TaskAdapter(lista){taskModel -> onItemSelected(taskModel)}
        binding.rvTask.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

//        when(taskModel.status){
//            TaskStatus.Finished -> binding.tvMdid.text = "Terminado"
//            TaskStatus.InProgress -> binding.tvMdid.text = "inProgress"
//            TaskStatus.ToDo -> binding.tvMdid.text = "Por hace"
//        }
    }

    private fun onItemSelected(task: TaskModel) {
//        getLatLng(medio)
    }
}