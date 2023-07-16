package com.roman_druck.ampg_printing_room.db

import android.content.Context
import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.roman_druck.ampg_printing_room.entities.*

@Database(
    entities = [LibraryItem::class, OffsetNotes::class, StockBalance::class,
        StockOut::class, TechnikItem::class, TechnikState::class],
    version = 8,
    exportSchema = true,autoMigrations = [AutoMigration(from = 7, to = 8)]
)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? = null

        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "ampg_offset_printing"
                )
                    .createFromAsset("databases/ampg_offset_printing.db")

                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                Log.d("MyLog", "Database 'ampg_offset_printing' created successfully")
                instance
            }
        }
    }
}

