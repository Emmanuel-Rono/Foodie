package com.emmanuel_rono.fav_dish.Data.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emmanuel_rono.fav_dish.Data.fav_Dish
import kotlinx.coroutines.flow.Flow


@Dao
interface  favDAO {
    @Insert
   suspend fun insertFavDishDetails(favDish: fav_Dish)
   @Query("SELECT * FROm fav_dish_table ORDER BY ID")
   fun getAllDishList() : Flow<List<fav_Dish>>
}