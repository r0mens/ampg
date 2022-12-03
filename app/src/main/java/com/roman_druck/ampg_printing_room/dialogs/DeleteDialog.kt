package com.roman_druck.ampg_printing_room.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.roman_druck.ampg_printing_room.databinding.DeleteDialogBinding

object DeleteDialog {

    fun showDialog(context: Context, listener: Listener){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DeleteDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            bDelete.setOnClickListener {
                listener.onClick()
                dialog?.dismiss()
            }
            bCancel.setOnClickListener {
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface Listener{
        fun onClick()
    }
}