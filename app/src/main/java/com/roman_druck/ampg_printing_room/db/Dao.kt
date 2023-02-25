package com.roman_druck.ampg_printing_room.db

import androidx.room.*
import androidx.room.Dao
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import com.roman_druck.ampg_printing_room.entities.TechnikItem
import com.roman_druck.ampg_printing_room.entities.TechnikState
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Query("SELECT * FROM offset_notes")
    fun getAllNotes(): Flow<List<OffsetNotes>>
    @Query("SELECT * FROM technik_state")
    fun getAllTechnikListNames(): Flow<List<TechnikState>>
    @Query("DELETE  FROM offset_notes WHERE id IS :id")
    suspend fun deleteNote(id: Int)
    @Query("DELETE  FROM technik_state WHERE id IS :id")
    suspend fun deleteTechnikListName(id: Int)
    @Insert
    suspend fun insertNote(note: OffsetNotes)
    @Insert
    suspend fun insertTechnikListName(name: TechnikState)
    @Insert
    suspend fun insertTechnikItem(technikItem: TechnikItem)
    @Update
    suspend fun updateNote(note: OffsetNotes)
    @Update
    suspend fun updateTechnikListName(listname: TechnikState)


}