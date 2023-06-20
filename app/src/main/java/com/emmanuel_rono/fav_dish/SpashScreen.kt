package com.emmanuel_rono.fav_dish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.emmanuel_rono.fav_dish.databinding.ActivitySpashScreenBinding

class SpashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create binding view
        val splashBinding:ActivitySpashScreenBinding=ActivitySpashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
}
}