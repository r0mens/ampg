package com.roman_druck.ampg_printing_room.db

import androidx.lifecycle.*
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import kotlinx.coroutines.launch

class MainViewModel(database: MainDataBase): ViewModel() {

    private val dao = database.getDao()
    val allNotes: LiveData<List<OffsetNotes>> = dao.getAllNotes().asLiveData()


    fun insertNote(note: OffsetNotes) = viewModelScope.launch {
        dao.insertNote(note)
    }








    fun updateNote(note: OffsetNotes) = viewModelScope.launch {
        dao.updateNote(note)
    }


    fun deleteNote(id: Int) = viewModelScope.launch {
        dao.deleteNote(id)
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