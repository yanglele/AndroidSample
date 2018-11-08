package com.example.yangl.androidsample.activity;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.contentObserver.ContentObserverFragment;

/**
 * 在activity中可以使用 重写onKeyDown来监听音量的变化，在fragment中可以使用ContentObserver来监听
 */
public class VolumeListenerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_listener);

        ContentObserverFragment fragment = new ContentObserverFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.in_fragment,fragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (keyCode){
            //此处估计是在获取系统配置之前调用
            case KeyEvent.KEYCODE_VOLUME_DOWN :
                int volumeDown = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                if(volumeDown == 1){
                    Toast.makeText(this,"mute",Toast.LENGTH_SHORT).show();
                }
                return false;
            case KeyEvent.KEYCODE_VOLUME_UP :
                int volumeUp = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                if(volumeUp == 0){
                    Toast.makeText(this,"unmute",Toast.LENGTH_SHORT).show();
                }
                return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
