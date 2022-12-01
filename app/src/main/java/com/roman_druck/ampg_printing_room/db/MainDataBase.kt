package com.roman_druck.ampg_printing_room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.roman_druck.ampg_printing_room.entities.LibraryItem
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import com.roman_druck.ampg_printing_room.entities.StockBalance
import com.roman_druck.ampg_printing_room.entities.StockOut


@Database(entities = [LibraryItem::class, OffsetNotes::class, StockBalance::class, StockOut::class],
               version = 1, exportSchema = true//,autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBase(context: Context): MainDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "ampg_offset_printing.db"
                ).build()
                instance
            }
        }
    }
}