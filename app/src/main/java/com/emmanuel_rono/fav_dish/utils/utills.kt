package com.emmanuel_rono.fav_dish.utils

object Constants {
    const val DISH_TYPE:String="Dish_Type"
    const val DISH_CATEGORY:String="Dish_Category"
    const val DISH_COOKING:String="Dish_Type"

    //Create the data
    fun dishType():ArrayList<String>
    {
        val list=ArrayList<String>()
        list.add("breakfast")
        list.add("Dinner")
        list.add("Supper")
        list.add("Lunch")
        list.add("Others")
        return list
    }
    fun FoodTypes():ArrayList<String>
    {
        val list=ArrayList<String>()
        list.add("Bannanas")
        list.add("Sandwich")
        list.add("Drinks")
        list.add("Salads")
        list.add("Tea and Coffess")
        list.add("Fruits")
        list.add("Bakery")
        list.add("Wraps")
        list.add("Pizza")
        list.add("Avacado")
        list.add("Melon")
        list.add("Ugali")
    }
}