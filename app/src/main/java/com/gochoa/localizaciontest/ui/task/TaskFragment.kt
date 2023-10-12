package com.gochoa.localizaciontest.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gochoa.localizaciontest.data.local.UIState
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.databinding.FragmentTaskBinding
import com.gochoa.localizaciontest.domain.model.TaskModel
import com.gochoa.localizaciontest.ui.task.adapter.TaskAdapter
import com.gochoa.localizaciontest.ui.task.component.AddTaskDialog
import com.gochoa.localizaciontest.utils.DateFormatted.getDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskAdapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun initUI() {
        taskAdapter = TaskAdapter(onItemSelected = {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
        })
        binding.rvTask.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initUI()
        clickEvents()
        buildObservers()
    }

    private fun buildObservers() {
        viewModel.taskList.observe(requireActivity()) {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {}
                is UIState.Success -> {
                    fillData(it.data!!)
                }
            }
        }
    }

    private fun fillData(taskList: MutableList<TaskEntity>) {
        taskAdapter = TaskAdapter(taskList, onItemSelected = {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
        })
        binding.rvTask.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun clickEvents() {
        binding.fab.setOnClickListener {
            AddTaskDialog(onSubmitClickListener = { title, description ->
                val task = TaskEntity(
                    title = title,
                    date = getDate(requireContext()),
                    description = description
                )
                viewModel.insertTask(task)
            }
            ).show(parentFragmentManager, "dialog")
        }
    }
}