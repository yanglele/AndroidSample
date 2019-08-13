package com.example.yangl.androidsample.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.DebugUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.someActivity.ActionMatchActivity;
import com.example.yangl.androidsample.someActivity.InnerClassActivity;
import com.example.yangl.androidsample.someActivity.LayoutMarginParamActivity;
import com.example.yangl.androidsample.someActivity.ScreenOrientationTestActivity;
import com.example.yangl.androidsample.someActivity.ScrollToActivity;
import com.example.yangl.androidsample.someActivity.SpannableActivity;
import com.example.yangl.androidsample.someActivity.TaskHeapAllowParentActivity1;
import com.example.yangl.androidsample.someActivity.TextViewScrollToActivity;
import com.example.yangl.androidsample.someActivity.ToastDestoryActivity;
import com.example.yangl.androidsample.someActivity.ViewLifeCycleActivity;
import com.example.yangl.androidsample.testActivity.ConfigChangeActivity;
import com.example.yangl.androidsample.tools.DebugUtil;
import com.example.yangl.androidsample.tools.DevUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SomeTestActivity extends AppCompatActivity {

    public static final int CALL_PHONE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_test);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void onConfigChangeBtnClick(View view) {
        startActivity(new Intent(SomeTestActivity.this, ConfigChangeActivity.class));
    }

    public void onRetainFragmentBtnClick(View view) {

    }

    public void onScreenOrientationChange(View view) {
        startActivity(new Intent(SomeTestActivity.this, ScreenOrientationTestActivity.class));
    }

    public void onTaskReparent(View view) {
        startActivity(new Intent(SomeTestActivity.this, TaskHeapAllowParentActivity1.class));
    }

    public void onActionMatch(View view) {
        Intent intent = new Intent();
        intent.setAction("com.yl.actionmatch.action1");
        startActivity(intent);
    }

    public void onLinearLayout(View view) {
        startActivity(new Intent(SomeTestActivity.this, ActionMatchActivity.class));
    }

    public void onSpannable(View view) {
        startActivity(new Intent(SomeTestActivity.this, SpannableActivity.class));
    }

    public void onScrollClick(View view) {
        startActivity(new Intent(SomeTestActivity.this, ScrollToActivity.class));
    }

    //设置Layout Param参数是否会影响其他参数
    public void onLayoutMarginClick(View view) {
        startActivity(new Intent(SomeTestActivity.this, LayoutMarginParamActivity.class));
    }

    public void onInnerClassClick(View view) {
        startActivity(new Intent(SomeTestActivity.this, InnerClassActivity.class));
    }

    public void onViewLifeClick(View view) {
        startActivity(new Intent(SomeTestActivity.this, ViewLifeCycleActivity.class));
    }

    public void onScrollToClick(View view) {
        startActivity(new Intent(SomeTestActivity.this, TextViewScrollToActivity.class));
    }

    public void onCallPhone(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 111);
        }else {
            call(this,"1233213,123");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111) {
            call(this,"1233213,123");
        }
    }

    public static void call(Context context, String phoneNumber) {

        phoneNumber = phoneNumber.replace(" ", "");
        String cmd = "am start -a android.intent.action.DIAL -d tel:".concat(phoneNumber);
        try {
            if (DevUtil.hasJellyBean4_2()) {
                //modify by ymj 9.1点击打电话去拨号页面
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory("android.intent.category.DEFAULT");
                context.startActivity(intent);
            } else {
                Runtime.getRuntime().exec(cmd);
            }
        } catch (SecurityException e) {
            Log.e("PhoneCall", e.getClass().getSimpleName(), e);
        } catch (IOException e) {
            Log.e("PhoneCall", e.getClass().getSimpleName(), e);
        }

    }

    public void onToastCLick(View view){
        Context context;
        AlertDialog alertDialog = new AlertDialog.Builder(SomeTestActivity.this).create();
        alertDialog.show();
        startActivity(new Intent(SomeTestActivity.this, ToastDestoryActivity.class));
    }
}
