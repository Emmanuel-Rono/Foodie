package com.emmanuel_rono.fav_dish.Presentation.Activities

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.emmanuel_rono.fav_dish.R
import com.emmanuel_rono.fav_dish.databinding.ActivityAddUpdateDishBinding

class AddUpdateDish : AppCompatActivity() , View.OnClickListener{
     private lateinit var updateDishBinding:ActivityAddUpdateDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDishBinding=ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(updateDishBinding.root)
        setupActionbar()
        updateDishBinding.dishImage.setOnClickListener(this)
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

}
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id){
                R.id.add_dish_image -> {
                    Toast.makeText(this,"Cmaera Clicked",Toast.LENGTH_SHORT).show()
                    return
                }

            }
        }
    }
}