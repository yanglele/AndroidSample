package com.example.yangl.androidsample.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.yangl.androidsample.R
import kotlinx.android.synthetic.main.activity_test_yl.*
import kotlinx.coroutines.*
class TestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_yl)
    }

    fun remove(view: View) {
        myRoot.removeView(viewGroup)
    }

    fun main() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
        Thread.currentThread().id
//        GlobalScope.launch(Dispatchers.Main){
//            val image = spendingGetImage("imageId")
//            imageView.setImageBitmap(image as Bitmap)
//        }
    }

//    suspend fun spendingGetImage(imageId:String){
//        withContext(Dispatchers.IO){
//        }
//    }


}