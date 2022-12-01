package com.roman_druck.ampg_printing_room.db

import androidx.room.*
import androidx.room.Dao
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Query("SELECT * FROM offset_notes")
    fun getAllNotes(): Flow<List<OffsetNotes>>
    @Query("DELETE  FROM offset_notes WHERE id IS :id")
    suspend fun deleteNote(id: Int)
    @Insert
    suspend fun insertNote(note: OffsetNotes)
    @Update
    suspend fun updateNote(note: OffsetNotes)


}