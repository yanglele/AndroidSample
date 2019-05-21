package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.someActivity.ActionMatchActivity;
import com.example.yangl.androidsample.someActivity.InnerClassActivity;
import com.example.yangl.androidsample.someActivity.LayoutMarginParamActivity;
import com.example.yangl.androidsample.someActivity.ScreenOrientationTestActivity;
import com.example.yangl.androidsample.someActivity.ScrollToActivity;
import com.example.yangl.androidsample.someActivity.SpannableActivity;
import com.example.yangl.androidsample.someActivity.TaskHeapAllowParentActivity1;
import com.example.yangl.androidsample.someActivity.TextViewScrollToActivity;
import com.example.yangl.androidsample.someActivity.ViewLifeCycleActivity;
import com.example.yangl.androidsample.testActivity.ConfigChangeActivity;


public class SomeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_test);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void onConfigChangeBtnClick(View view){
        startActivity(new Intent(SomeTestActivity.this, ConfigChangeActivity.class));
    }

    public void onRetainFragmentBtnClick(View view){

    }

    public void onScreenOrientationChange(View view){
        startActivity(new Intent(SomeTestActivity.this,ScreenOrientationTestActivity.class));
    }

    public void onTaskReparent(View view){
        startActivity(new Intent(SomeTestActivity.this,TaskHeapAllowParentActivity1.class));
    }

    public void onActionMatch(View view){
        Intent intent = new Intent();
        intent.setAction("com.yl.actionmatch.action1");
        startActivity(intent);
    }

    public void onLinearLayout(View view){
        startActivity(new Intent(SomeTestActivity.this,ActionMatchActivity.class));
    }

    public void onSpannable(View view){
        startActivity(new Intent(SomeTestActivity.this, SpannableActivity.class));
    }

    public void onScrollClick(View view){
        startActivity(new Intent(SomeTestActivity.this, ScrollToActivity.class));
    }

    //设置Layout Param参数是否会影响其他参数
    public void onLayoutMarginClick(View view){
        startActivity(new Intent(SomeTestActivity.this, LayoutMarginParamActivity.class));
    }

    public void onInnerClassClick(View view){
        startActivity(new Intent(SomeTestActivity.this, InnerClassActivity.class));
    }

    public void onViewLifeClick(View view){
        startActivity(new Intent(SomeTestActivity.this, ViewLifeCycleActivity.class));
    }

    public void onScrollToClick(View view){
        startActivity(new Intent(SomeTestActivity.this, TextViewScrollToActivity.class));
    }
}
