package com.roman_druck.ampg_printing_room.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.databinding.ActivityTechnikListBinding
import com.roman_druck.ampg_printing_room.db.MainViewModel
import com.roman_druck.ampg_printing_room.entities.TechnikItem
import com.roman_druck.ampg_printing_room.entities.TechnikState
import com.roman_druck.ampg_printing_room.dialogs.NewTechnikListDialog
import com.roman_druck.ampg_printing_room.fragments.TechnikListFragment
import kotlinx.coroutines.flow.firstOrNull

class TechnikListActivity : AppCompatActivity(){
    private lateinit var binding: ActivityTechnikListBinding
    private var listId: Int = 0
    private var technikDescription: String? = null


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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_technik_list -> {
                binding.tvTest.visibility = View.VISIBLE
                showAddDescriptionDialog()
                Log.d("MyLog", "кнопка нажата")
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

    private fun showAddDescriptionDialog() {
        NewTechnikListDialog.showDialog(this, object : NewTechnikListDialog.Listener {
            override fun onSave(description: String) {
                lifecycleScope.launch {
                    addNewTechnikItem(description)
                    val resultIntent = Intent().apply {
                        putExtra(TechnikListActivity.DESCRIPTION_EXTRA, description)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                }
            }
        })
    }




    private suspend fun addNewTechnikItem(description: String) {
        if (!description.isNullOrBlank()) {
            val existingItem = mainViewModel.getTechnikItemByListId(listId)
            if (existingItem != null) {
                existingItem.description = description
                mainViewModel.updateTechnikItem(existingItem)
                binding.tvTest.text = description
            } else {
                val newItem = TechnikItem(null, listId, description)
                val newItemId = mainViewModel.insertTechnikItem(newItem)
                val listName = mainViewModel.observeTechnikListNameById(listId).firstOrNull()
                if (listName != null) {
                    val updatedItemsIds = listName.itemsIds?.toMutableList() ?: mutableListOf()
                    updatedItemsIds.add(newItemId.toInt()) // Convert newItemId to Int explicitly
                    val updatedListName = listName.copy(itemsIds = updatedItemsIds)
                    mainViewModel.updateTechnikListName(updatedListName)
                }
                binding.tvTest.text = description
            }
        } else if (binding.tvTest.text.toString().isEmpty()) {
            Toast.makeText(this, "Введите название элемента", Toast.LENGTH_SHORT).show()
        }
    }



    private fun init() {
        listId = intent.getIntExtra(LIST_ID_EXTRA, 0)
        lifecycleScope.launch {
            observeTechnikListNameById(listId)
        }
    }

    private suspend fun observeTechnikListNameById(id: Int) {
        mainViewModel.observeTechnikListNameById(id).collect { technikState ->
            val itemsIds = technikState?.itemsIds
            binding.tvName.text = technikState?.name_list
            binding.tvTest.text = getTechnikItemsDescription(itemsIds)
        }
    }
    private suspend fun getTechnikItemsDescription(itemsIds: List<Int>?): String {
        val items = mutableListOf<TechnikItem>()
        itemsIds?.forEach { itemId ->
            val item = mainViewModel.getTechnikItemById(itemId)
            if (item != null) {
                items.add(item)
            }
        }
        return items.joinToString(", ") { it.description }
    }


    companion object {
        const val DESCRIPTION_EXTRA = "description_extra"
        const val LIST_ID_EXTRA = "list_id_extra"
        const val ITEM_IDS_EXTRA = "item_ids_extra"

    }


}
