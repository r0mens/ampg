package com.roman_druck.ampg_printing_room.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.roman_druck.ampg_printing_room.databinding.NewTechnikListDialogBinding

object NewTechnikListDialog {
    interface Listener {
        fun onSave(description: String)
    }
    fun showDialog(context: Context, listener: Listener) {
        val binding = NewTechnikListDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        binding.btSave.setOnClickListener {
            val description = binding.desk.text.toString()
            if (description.isNotEmpty()) {
                listener.onSave(description)
            }
            dialog.dismiss()
        }

        dialog.show()
    }
}