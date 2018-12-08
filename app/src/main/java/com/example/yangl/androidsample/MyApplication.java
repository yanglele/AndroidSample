package com.example.yangl.androidsample;

import android.app.Application;
import android.util.Log;

import com.example.yangl.androidsample.tools.AjkImageLoaderUtil;
import com.example.yangl.androidsample.tools.DebugUtil;
import com.example.yangl.androidsample.MyEventBusIndex;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

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

        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }

        LeakCanary.install(this);

        DebugUtil.initialize(getApplicationContext());
        AjkImageLoaderUtil.getInstance().init(getApplicationContext());
        ButterKnife.setDebug(true);
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        Log.d(TAG, "onCreate: ");
    }
}
