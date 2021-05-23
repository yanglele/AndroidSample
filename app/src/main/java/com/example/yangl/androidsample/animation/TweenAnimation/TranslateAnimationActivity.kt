package com.example.yangl.androidsample.animation.TweenAnimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.TranslateAnimation
import android.widget.Toast
import com.example.yangl.androidsample.R
import kotlinx.android.synthetic.main.activity_translate_animation.*

class TranslateAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate_animation)
        button.setOnClickListener{
            val animation = TranslateAnimation(button.x,button.y,button.x+150,button.y+150)
            animation.fillAfter = true
            button.startAnimation(animation)
            Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
        }
    }
}