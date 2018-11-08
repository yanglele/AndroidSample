package com.example.yangl.androidsample.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;

public class MusicServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test1);
    }

    public void beginService(View view){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void pauseService(View view){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.PAUSE);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void stopService(View view){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.STOP);
        intent.putExtras(bundle);
        startService(intent);
    }
}
