package com.roman_druck.ampg_printing_room.db

import androidx.lifecycle.*
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import com.roman_druck.ampg_printing_room.entities.TechnikState
import kotlinx.coroutines.launch

class MainViewModel(database: MainDataBase): ViewModel() {

    private val dao = database.getDao()
    val allNotes: LiveData<List<OffsetNotes>> = dao.getAllNotes().asLiveData()
    val allTechnikListNames: LiveData<List<TechnikState>> = dao.getAllTechnikListNames(). asLiveData()
    fun insertNote(note: OffsetNotes) = viewModelScope.launch {
        dao.insertNote(note)
    }
    fun insertTechnikListName(listName: TechnikState) = viewModelScope.launch {
        dao.insertTechnikListName(listName)
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