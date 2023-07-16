package com.roman_druck.ampg_printing_room.db

import androidx.lifecycle.*
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import com.roman_druck.ampg_printing_room.entities.TechnikItem
import com.roman_druck.ampg_printing_room.entities.TechnikState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(database: MainDataBase): ViewModel() {

    private val dao = database.getDao()
    val allNotes: LiveData<List<OffsetNotes>> = dao.getAllNotes().asLiveData()
    val allTechnikListNames: LiveData<List<TechnikState>> = dao.getAllTechnikListNames(). asLiveData()

    fun insertNote(note: OffsetNotes) = viewModelScope.launch {
        dao.insertNote(note)
    }
    suspend fun observeTechnikListNameById(id: Int): Flow<TechnikState?> {
        return withContext(Dispatchers.IO) {
            dao.getTechnikListNameById(id)
        }
    }

    suspend fun getTechnikItemById(itemId: Int): TechnikItem? {
        return dao.getTechnikItemById(itemId)
    }

    suspend fun getTechnikItemByListId(listId: Int): TechnikItem? {
        return withContext(Dispatchers.IO) {
            dao.getTechnikItemByListId(listId)
        }
    }


    suspend fun updateTechnikItem(item: TechnikItem) {
        withContext(Dispatchers.IO) {
            dao.updateTechnikItem(item)
        }
    }





    fun insertTechnikListName(listName: TechnikState) = viewModelScope.launch {
        dao.insertTechnikListName(listName)
    }
    suspend fun insertTechnikItem(technikItem: TechnikItem): Long {
        return withContext(Dispatchers.IO) {
            val newItemId = dao.insertTechnikItem(technikItem)
            newItemId.toLong()
        }
    }



    fun updateNote(note: OffsetNotes) = viewModelScope.launch {
        dao.updateNote(note)
    }
    fun updateTechnikListName(listname: TechnikState) = viewModelScope.launch {
        dao.updateTechnikListName(listname)
    }

    fun deleteNote(id: Int) = viewModelScope.launch {
        dao.deleteNote(id)
    }
    fun deleteTechnikListName(id: Int) = viewModelScope.launch {
        dao.deleteTechnikListName(id)
    }
    fun deleteTechnikItem(listId: Int) = viewModelScope.launch{
        dao.deleteTechnikItem(listId)
    }
    class MainViewModelFactory(private val database: MainDataBase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}