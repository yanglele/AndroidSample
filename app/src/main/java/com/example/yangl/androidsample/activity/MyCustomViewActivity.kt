package com.example.yangl.androidsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.yangl.androidsample.R
import kotlinx.android.synthetic.main.my_custom_view_activity.*

class MyCustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_custom_view_activity)
    }

    override fun onResume() {
        super.onResume()
        createViewGroup()
    }

    private fun createViewGroup(){
        val strs = arrayOf( "哲学系","新疆维吾尔族自治区", "新闻学", "心理学",
        "犯罪心理学","明明白白", "西方文学史", "计算机")
        for (text in strs){
            val textView = TextView(this)
            textView.text = text
            wordView.addView(textView)
        }
    }
}