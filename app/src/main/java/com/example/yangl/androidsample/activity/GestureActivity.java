package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.GestureDector.GestureDectorActivity;
import com.example.yangl.androidsample.R;

public class GestureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
    }

    public void onSimpleGestureViewClicked(View view){
        Intent intent = new Intent(GestureActivity.this, GestureDectorActivity.class);
        startActivity(intent);
    }
}
