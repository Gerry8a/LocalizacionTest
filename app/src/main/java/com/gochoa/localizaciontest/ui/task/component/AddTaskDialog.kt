package com.gochoa.localizaciontest.ui.task.component

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.gochoa.localizaciontest.databinding.DialogAddingBinding

class AddTaskDialog(
    private val onSubmitClickListener: (String, String) -> Unit
): DialogFragment() {

    private lateinit var binding : DialogAddingBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddingBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)


        binding.btnAddTask.setOnClickListener {
            onSubmitClickListener.invoke(binding.etTitle.text.toString(), binding.etDescription.text.toString())
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}