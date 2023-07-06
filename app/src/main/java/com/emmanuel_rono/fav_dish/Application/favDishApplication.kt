package com.emmanuel_rono.fav_dish.Application

import android.app.Application
import com.emmanuel_rono.fav_dish.Data.Database.favDishDatabase
import com.emmanuel_rono.fav_dish.Data.Database.favDishRepository

class favDishApplication : Application()
{
    private val database by lazy {favDishDatabase.getDatabase(this@favDishApplication)}
    val repository by lazy {favDishRepository(database.favDao())}
}