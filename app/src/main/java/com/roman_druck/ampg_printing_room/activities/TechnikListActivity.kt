package com.roman_druck.ampg_printing_room.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
        //newItem?.isVisible = false

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_technik_list -> {
                binding.edAddTechnikItem.visibility = View.VISIBLE
                binding.buttonSave.visibility = View.VISIBLE
                binding.tvTest.visibility = View.INVISIBLE
                Log.d("MyLog","кнопка нажата")
            }
            android.R.id.home -> {
                finish()
            }
            R.id.delete_technik_list ->{
                Log.d("MyLog", "delete_technik_list")
            }
            R.id.clear_technik_list ->{
                Log.d("MyLog", "clear_technik_list")
            }
            R.id.share_technik_list ->{
                Log.d("MyLog", "share_technik_list")
            }


        }
        return super.onOptionsItemSelected(item)
    }
    private fun addNewTechnikItem() = with(binding) {
        if (edAddTechnikItem?.text.toString().isNotEmpty()) {
            val itemT = TechnikItem(
                null,
                edAddTechnikItem?.text.toString(),
                technikstate?.id!!
            )
            mainViewModel.insertTechnikItem(itemT)
        } else {
            Toast.makeText(this@TechnikListActivity, "Введите название элемента", Toast.LENGTH_SHORT).show()
        }
    }


    private  fun init(){
        technikstate = intent.getSerializableExtra(TECHNIK_STATE) as TechnikState
        binding.tvTest.text = technikstate?.name_list
    }
    companion object{
        const val TECHNIK_STATE = "technik_state"
    }
}