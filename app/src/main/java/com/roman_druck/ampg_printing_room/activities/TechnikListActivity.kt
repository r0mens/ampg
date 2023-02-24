package com.roman_druck.ampg_printing_room.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.activity.viewModels
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.databinding.ActivityTechnikListBinding
import com.roman_druck.ampg_printing_room.db.MainViewModel
import com.roman_druck.ampg_printing_room.entities.TechnikItem
import com.roman_druck.ampg_printing_room.entities.TechnikState

class TechnikListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTechnikListBinding
    private var technikstate: TechnikState? = null
    private var edItem: TechnikItem? = null
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTechnikListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        actionBarSettings()
    }
    private fun actionBarSettings() {
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.technik_menu, menu)
        val newItem = menu?.findItem(R.id.new_technik_list)
        //newItem?.isVisible = false

        return true
    }
    private fun addNewTechnikItem() = with(binding){
        edAddTechnikItem.setText(edItem?.name)

    }
    private  fun init(){
        technikstate = intent.getSerializableExtra(TECHNIK_STATE) as TechnikState
        binding.tvTest.text = technikstate?.name_list
    }
    companion object{
        const val TECHNIK_STATE = "technik_state"
    }
}