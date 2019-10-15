package com.example.yangl.androidsample.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Desc:IntentService会创建子线程来运行该任务，启动后无法通过stopService来终止。
 * 多次点击启动服务不会调用两次onCreate方法，但是会加入队列来执行两次该任务
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/4/12
 * version:
 * update:
 */

public class MyIntentService extends IntentService {

    public static final String ACTION_TYPE_THREAD = "action_type_thread";
    public static final String ACTION_TYPE_SERVICE = "action_type_service";

    public static final String STATUS = "status";
    public static final String PROGRESS = "progress";
    private int count;
    private boolean isRunning;

    private LocalBroadcastManager localBroadcastManager;


    public MyIntentService() {
        super("myIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        sendServiceStatus("服务启动");
        Log.d("MyIntentService", "onCreate: ");
        Log.d("MyIntentService11", "onHandleIntent: "+Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Log.d("MyIntentService", "onHandleIntent: "+Thread.currentThread().getName());
            sendServiceStatus("服务运行中...");
            sendThreadStatus("线程启动中...",count);

            isRunning = true;
            count = 0;
            while (isRunning){
                count++;
                if(count >= 100){
                    isRunning =false;
                }
                Thread.sleep(50);
                sendThreadStatus("线程运行中...",count);
            }
            sendThreadStatus("线程结束",count);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sendServiceStatus("服务结束");
        Log.d("MyIntentService", "onDestroy: ");
    }

    private void sendServiceStatus(String status){
        Intent intent = new Intent(ACTION_TYPE_SERVICE);
        intent.putExtra("status",status);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void sendThreadStatus(String status,int progress){
        Intent intent = new Intent(ACTION_TYPE_THREAD);
        intent.putExtra(STATUS,status);
        intent.putExtra(PROGRESS,progress);
        localBroadcastManager.sendBroadcast(intent);
    }
}
