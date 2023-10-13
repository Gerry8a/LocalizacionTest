package com.gochoa.localizaciontest.ui.task

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gochoa.localizaciontest.R
import com.gochoa.localizaciontest.data.local.UIState
import com.gochoa.localizaciontest.data.local.entity.TaskEntity
import com.gochoa.localizaciontest.databinding.FragmentTaskBinding
import com.gochoa.localizaciontest.service.DefaultLocationClient
import com.gochoa.localizaciontest.service.LocationClient
import com.gochoa.localizaciontest.service.LocationService
import com.gochoa.localizaciontest.ui.task.adapter.TaskAdapter
import com.gochoa.localizaciontest.ui.task.component.AddTaskDialog
import com.gochoa.localizaciontest.utils.DateFormatted.getDate
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskAdapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels()
    private var locationPermissionAccepted = false
    private lateinit var locationClient: LocationClient
    private var serviceOn = false
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocationPermission()
        clickEvents()
        buildObservers()

        locationClient = DefaultLocationClient(
            requireContext(),
            LocationServices.getFusedLocationProviderClient(requireContext())
        )
    }

    private fun updateCoordinates() {
        lifecycleScope.launch {
            val result = locationClient.getLocationUpdates()
            result.collect {
                showCoordinates(it)
                showSpeed(it)
            }
        }
    }

    private fun showSpeed(it: Location) {
        binding.tvSpeedValue.isVisible = true
        val speed = it.speed.toDouble()

        val a: Double = 3.6 * speed
        val kmhSpeed = Math.round(speed).toInt()
        binding.tvSpeedValue.text = getString(R.string.kmHr, kmhSpeed.toString())
    }

    private fun showCoordinates(it: Location) {
        binding.tvCoordinates.isVisible = true
        binding.tvCoordinates.text = getString(
            R.string.coordinates_value,
            it.latitude.toString(),
            it.longitude.toString()
        )
    }

    private fun askNotification() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS
            ),
            0
        )
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
            viewModel.updateTask(it)
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
                if (task.title.isEmpty() || task.description.isEmpty()){
                    Toast.makeText(requireContext(), "Falta información", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertTask(task)
                }
            }
            ).show(parentFragmentManager, "dialog")
        }

        binding.servicio.setOnClickListener {
            if (!serviceOn){
                val intent = Intent(requireContext(), LocationService::class.java)
                intent.action = LocationService.ACTION_START
                requireContext().startService(intent)
                updateCoordinates()
                Toast.makeText(requireContext(), "Iniciando servicio", Toast.LENGTH_SHORT).show()
                serviceOn = true
            } else {
                val intent = Intent(requireContext(), LocationService::class.java)
                intent.action = LocationService.ACTION_STOP
                requireContext().startService(intent)
                updateCoordinates()
                serviceOn = false
                Toast.makeText(requireContext(), "Terminando servicio", Toast.LENGTH_SHORT).show()
            }
            
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                locationPermissionAccepted = true
                askNotification()
            } else {
                locationPermissionAccepted = false
                Toast.makeText(requireContext(),
                    getString(R.string.permission_needed), Toast.LENGTH_SHORT).show()
                askNotification()
            }
        }

    fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                locationPermissionAccepted = true
                askNotification()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                /**
                 * Descripción de porqué debe aceptar el permiso
                 */
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
           Toast.makeText(requireContext(),
                    getString(R.string.permission_needed), Toast.LENGTH_SHORT).show()
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(requireContext(), LocationService::class.java)
        intent.action = LocationService.ACTION_STOP
        requireContext().startService(intent)
    }
}