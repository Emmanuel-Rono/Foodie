package com.emmanuel_rono.fav_dish.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emmanuel_rono.fav_dish.Data.fav_Dish

@Database(entities = [fav_Dish::class], version = 1)
abstract  class favDishDatabase :RoomDatabase()
{
    abstract fun favDao():favDAO

    companion object {
        @Volatile

        private var database: favDishDatabase? = null

        fun getDatabase(context: Context): favDishDatabase {
            if (database == null) {
                synchronized(favDishDatabase::class) {
                    //if database instance is empty-Not yet created then create one
                    if (database == null) {
                        database = Room.databaseBuilder(
                            context.applicationContext,
                            favDishDatabase::class.java,
                            "fav_dish_database"
                        ).build()
                    }
                }
            }
            return database!!
        }

    }
}