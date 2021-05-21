package com.example.yangl.androidsample;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.yangl.androidsample.tools.AjkImageLoaderUtil;
import com.example.yangl.androidsample.tools.DebugUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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

public class MyApplication extends MultiDexApplication {
    private final String TAG = MyApplication.class.getSimpleName();
    public static  RefWatcher refWatcher;
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        applicationContext = getBaseContext();
        refWatcher = LeakCanary.install(this);
        DebugUtil.initialize(getApplicationContext());
        AjkImageLoaderUtil.getInstance().init(getApplicationContext());
        ButterKnife.setDebug(true);
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        Log.d(TAG, "onCreate: "+this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG,activity.getLocalClassName()+"  onCreate");
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG,activity.getLocalClassName()+"  onDestroyed");
            }
        });
    }


}
