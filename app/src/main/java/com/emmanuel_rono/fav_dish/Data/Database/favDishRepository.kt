package com.emmanuel_rono.fav_dish.Data.Database

import androidx.annotation.WorkerThread
import com.emmanuel_rono.fav_dish.Data.fav_Dish

class favDishRepository(private  val favDAO: favDAO)
{
    @WorkerThread
    suspend fun insertFavDishData(favDish: fav_Dish)
    {
        favDAO.insertFavDishDetails(favDish)
    }

}