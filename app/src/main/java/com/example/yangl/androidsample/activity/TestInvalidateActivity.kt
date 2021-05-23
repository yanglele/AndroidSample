package com.example.yangl.androidsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.yangl.androidsample.R
import kotlinx.android.synthetic.main.activity_test_invalidate.*
import java.util.logging.Logger

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2021/5/23
 * version:
 * update:
 */
class TestInvalidateActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_invalidate)
        button.setOnClickListener{
            button.setBackgroundColor(resources.getColor(R.color.m70GreenColor))
            findViewById<View>(android.R.id.content).setBackgroundColor(resources.getColor(R.color.m50DarkBlackColor))
//            button.invalidate()

        }

        button1.setOnClickListener{
            button1.setBackgroundColor(resources.getColor(R.color.m70GreenColor))
            findViewById<View>(android.R.id.content).setBackgroundColor(resources.getColor(R.color.m50DarkBlackColor))

//            button1.requestLayout()
        }

        button2.setOnClickListener{
            button.width
            button1.width
        }

    }
}