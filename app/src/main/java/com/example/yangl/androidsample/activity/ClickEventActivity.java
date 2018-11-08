package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.someActivity.ClickEventTestActivity;

public class ClickEventActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_event);
    }

    public void onClickEvent(View view){
        startActivity(new Intent(ClickEventActivity.this, ClickEventTestActivity.class));
    }
}
