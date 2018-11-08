package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.service.IntentServiceActivity;
import com.example.yangl.androidsample.service.MusicServiceActivity;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void onTest1Click(View view){
        Intent intent = new Intent(this, MusicServiceActivity.class);
        startActivity(intent);
    }

    public void onTest2Click(View view){
        Intent intent = new Intent(this, IntentServiceActivity.class);
        startActivity(intent);
    }
}
