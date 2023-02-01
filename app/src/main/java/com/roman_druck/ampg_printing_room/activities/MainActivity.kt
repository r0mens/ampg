package com.roman_druck.ampg_printing_room.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.databinding.ActivityMainBinding
import com.roman_druck.ampg_printing_room.dialogs.NewListDialog
import com.roman_druck.ampg_printing_room.fragments.FragmentManager
import com.roman_druck.ampg_printing_room.fragments.OffsetNoteFragment
import com.roman_druck.ampg_printing_room.fragments.TechnikListFragment

class MainActivity : AppCompatActivity(), NewListDialog.Listener{
    private lateinit var binding: ActivityMainBinding
    //private var currentMenuItemId = R.id.offset
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(TechnikListFragment.newInstance(), this)
        setBottomNavListener()
    }
    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.offset -> {
                    Log.d("MyLog","offset")
                    //currentMenuItemId = R.id.offset
                    FragmentManager.setFragment(OffsetNoteFragment.newInstance(), this)
                }
                R.id.stock_out -> {
                    Log.d("MyLog","stock_out")
                }
                R.id.stock_balance -> {
                    Log.d("MyLog","stock_balance")
                }
                R.id.new_item -> {
                    FragmentManager.currentFrag?.onClickNew()

                }
                R.id.tecnik_state ->{
                    FragmentManager.setFragment(TechnikListFragment.newInstance(), this)
                }
            }
            true

        }
    }

    override fun onClick(name: String) {
        Log.d("MyLog", "Name: $name")
    }
}