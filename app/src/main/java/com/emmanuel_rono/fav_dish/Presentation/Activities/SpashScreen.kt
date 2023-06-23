package com.emmanuel_rono.fav_dish.Presentation.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.emmanuel_rono.fav_dish.R
import com.emmanuel_rono.fav_dish.databinding.ActivitySpashScreenBinding

class SpashScreen : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create binding view
        val splashBinding:ActivitySpashScreenBinding=ActivitySpashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else
        {
            @Suppress("DEPRECIATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAGS_CHANGED
            )
        }
    val splashScreen=AnimationUtils.loadAnimation(this, R.anim.animation_splash)
    splashBinding.splashext.animation=splashScreen
        splashScreen.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
               /*/TODO("Not yet implemented")*/
            }
            override fun onAnimationEnd(p0: Animation?) {
               Handler(Looper.getMainLooper()).postDelayed({startActivity(Intent(this@SpashScreen,
                   MainActivity::class.java))
                   finish()},
               1000)
            }
            override fun onAnimationRepeat(p0: Animation?) {
                TODO("Not yet implemented")
            }
        })
}}