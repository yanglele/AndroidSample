package com.example.yangl.androidsample.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/4/12
 * version:
 * update:
 */

public class MusicService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private MediaPlayer mediaPlayer;
    private int startId;

    public enum Control{
        PLAY,PAUSE,STOP
    }

    @Override
    public void onCreate() {
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.music1);
            mediaPlayer.setLooping(false);
        }
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.d(TAG, "onStartCommand: startId = "+startId);

        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Control control = (Control) bundle.getSerializable("key");
            if(control != null){
                switch (control){
                    case PLAY:
                        play();
                        break;
                    case PAUSE:
                        pause();
                        break;
                    case STOP:
                        stop();
                        break;
                    default:break;
                }
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void play(){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    private void pause(){
        if(mediaPlayer.isPlaying() && mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    private void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
