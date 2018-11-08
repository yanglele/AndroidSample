package com.example.yangl.androidsample;

import android.app.Application;
import android.util.Log;

import com.example.yangl.androidsample.tools.AjkImageLoaderUtil;
import com.example.yangl.androidsample.tools.DebugUtil;
import com.example.yangl.androidsample.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/3/9
 * version:
 * update:
 */

public class MyApplication extends Application {
    private final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        DebugUtil.initialize(getApplicationContext());
        AjkImageLoaderUtil.getInstance().init(getApplicationContext());
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        Log.d(TAG, "onCreate: ");
    }
}
