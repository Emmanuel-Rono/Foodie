package com.emmanuel_rono.fav_dish.Data.Database

import androidx.room.Dao
import androidx.room.Insert
import com.emmanuel_rono.fav_dish.Data.fav_Dish

@Dao
interface  favDAO {
    @Insert
   suspend fun insertFavDishDetails(favDish: fav_Dish)
}