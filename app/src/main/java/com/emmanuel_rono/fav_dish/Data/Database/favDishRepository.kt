package com.emmanuel_rono.fav_dish.Data.Database

import androidx.annotation.WorkerThread
import com.emmanuel_rono.fav_dish.Data.fav_Dish
import kotlinx.coroutines.flow.Flow

class favDishRepository(private  val favDAO: favDAO)
{
    @WorkerThread
    suspend fun insertFavDishData(favDish: fav_Dish)
    {
        favDAO.insertFavDishDetails(favDish)
    }
         val allDishesList: Flow<List<fav_Dish>> = favDAO.getAllDishList()

}