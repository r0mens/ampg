package com.roman_druck.ampg_printing_room.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.roman_druck.ampg_printing_room.databinding.ActivityTechnikListBinding
import com.roman_druck.ampg_printing_room.db.MainViewModel
import com.roman_druck.ampg_printing_room.entities.TechnikState

class TechnikListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTechnikListBinding
    private var technikstate: TechnikState? = null
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTechnikListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private  fun init(){
        technikstate = intent.getSerializableExtra(TECHNIK_STATE) as TechnikState
        binding.tvTest.text = technikstate?.name_list
    }
    companion object{
        const val TECHNIK_STATE = "technik_state"
    }
}