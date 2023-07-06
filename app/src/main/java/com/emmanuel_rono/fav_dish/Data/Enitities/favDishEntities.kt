package com.emmanuel_rono.fav_dish.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="fav_dish_table")
data class fav_Dish(
    //@ColumnInfo val image:String,
    @ColumnInfo val category: String,
    @ColumnInfo val type: String,
    @ColumnInfo val coking_time:String,
    @ColumnInfo val food_Bio:String,
   // @ColumnInfo val favourite:Boolean,
    @PrimaryKey(autoGenerate = true) val id:Int=0

)
