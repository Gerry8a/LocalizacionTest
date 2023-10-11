package com.gochoa.localizaciontest.ui.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gochoa.localizaciontest.R
import com.gochoa.localizaciontest.data.domain.model.TaskModel
import com.gochoa.localizaciontest.data.domain.model.TaskStatus
import com.gochoa.localizaciontest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}