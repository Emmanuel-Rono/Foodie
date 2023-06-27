package com.emmanuel_rono.fav_dish.Presentation.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emmanuel_rono.fav_dish.R
import com.emmanuel_rono.fav_dish.databinding.ActivityAddUpdateDishBinding

class AddUpdateDish : AppCompatActivity() {
     private lateinit var updateDishBinding:ActivityAddUpdateDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDishBinding=ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(updateDishBinding.root)
        setupActionbar()

    }
//Setting the Action Bar
    //create a function to do that
    private fun setupActionbar()
{
        setSupportActionBar(updateDishBinding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    //Set onclick Listerner
    updateDishBinding.toolbar.setNavigationOnClickListener{
    onBackPressed()

    }
}}