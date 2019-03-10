package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.broadcast.BroadcastActivity1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    public void jumpBroadcast(View view){
        startActivity(new Intent(this, BroadcastActivity1.class));
        List<String> stringList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
    }
}
