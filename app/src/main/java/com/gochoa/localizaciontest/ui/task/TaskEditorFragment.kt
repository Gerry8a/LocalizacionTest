package com.gochoa.localizaciontest.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gochoa.localizaciontest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskEditorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_editor, container, false)
    }


}