package com.roman_druck.ampg_printing_room.fragments

import androidx.appcompat.app.AppCompatActivity
import com.roman_druck.ampg_printing_room.R

object FragmentManager {
    var currentFrag: BaseFragment? = null

    fun setFragment(newFrag: BaseFragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFrag)
        transaction.commit()
        currentFrag = newFrag
    }
}