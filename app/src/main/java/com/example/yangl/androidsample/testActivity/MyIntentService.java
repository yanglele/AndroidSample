package com.example.yangl.androidsample.testActivity;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/24
 * version:
 * update:
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MyIntentService", "onHandleIntent: " + Thread.currentThread().getName());
    }
}
