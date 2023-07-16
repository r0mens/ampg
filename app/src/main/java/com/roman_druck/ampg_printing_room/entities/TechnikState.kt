

package com.roman_druck.ampg_printing_room.entities

import androidx.room.*
import java.io.Serializable


@Entity(tableName = "technik_state")
@TypeConverters(Converters::class)
data class TechnikState(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name_list")
    val name_list: String,
    @ColumnInfo(name = "data")
    val date: String,
    @ColumnInfo(name = "itemsIds")
    var itemsIds: List<Int>? = null
): Serializable