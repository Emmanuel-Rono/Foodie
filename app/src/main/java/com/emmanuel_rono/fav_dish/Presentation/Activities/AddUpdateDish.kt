package com.emmanuel_rono.fav_dish.Presentation.Activities

import android.app.Dialog
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.emmanuel_rono.fav_dish.R
import com.emmanuel_rono.fav_dish.databinding.ActivityAddUpdateDishBinding
import com.emmanuel_rono.fav_dish.databinding.PopupScreenAddDishBinding

class AddUpdateDish : AppCompatActivity() , View.OnClickListener{
     private lateinit var updateDishBinding:ActivityAddUpdateDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDishBinding=ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(updateDishBinding.root)
        setupActionbar()
        updateDishBinding.addDishImage.setOnClickListener(this)
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
    override fun onClick(V: View?) {
        if (V != null) {
            when(V.id){
                R.id.add_dish_image-> {
                    ImageSelectionDialog()
                    return
                }
            }
        }
    }
    private fun ImageSelectionDialog()
    {
        val dialog=Dialog(this)
        val binding:PopupScreenAddDishBinding=PopupScreenAddDishBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.addViaGallery.setOnClickListener{
            Toast.makeText(this,"Camera Clicked",Toast.LENGTH_LONG).show()
        }
        binding.addViaCamera.setOnClickListener{
            Toast.makeText(this,"Camera Clicked",Toast.LENGTH_LONG).show()
        }
        dialog.show()
    }
}