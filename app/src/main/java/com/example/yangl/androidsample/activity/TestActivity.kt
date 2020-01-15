package com.example.yangl.androidsample.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.example.yangl.androidsample.R
import kotlinx.android.synthetic.main.activity_test_yl.*

class TestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_yl)
    }

    fun remove(view: View) {
        myRoot.removeView(viewGroup)
    }


}