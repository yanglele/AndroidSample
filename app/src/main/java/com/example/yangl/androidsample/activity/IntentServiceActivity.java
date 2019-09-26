package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.testActivity.MyIntentService;

public class IntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service2);

        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }
}
