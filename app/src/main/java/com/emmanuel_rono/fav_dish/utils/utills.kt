package com.emmanuel_rono.fav_dish.utils

object Constants {
    const val DISH_TYPE:String="Dish_Type"
    const val DISH_CATEGORY:String="Dish_Category"
    const val DISH_COOKING_TIME:String="Dish_Type"

    //Create the data
    fun dishCategory():ArrayList<String>
    {
        val list=ArrayList<String>()
        list.add("breakfast")
        list.add("Dinner")
        list.add("Supper")
        list.add("Lunch")
        list.add("Others")
        return list
    }
    fun dish_Names():ArrayList<String>
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
        return list
    }
    fun CookTime():ArrayList<String>{

        val list= ArrayList<String>()
        list.add("10")
        list.add("20")
        list.add("130")
        list.add("40")
        list.add("50")
        list.add("60")
        list.add("70")
        list.add("80")
        list.add("90")
        return list
    }
}