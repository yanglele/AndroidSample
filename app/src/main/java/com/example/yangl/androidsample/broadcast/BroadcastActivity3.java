package com.example.yangl.androidsample.broadcast;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;

public class BroadcastActivity3 extends AppCompatActivity {

    public static final String  BROADCAST_MY = "broad_cast_my";
    public static final String  BROADCAST_MY_1 = "broad_cast_my_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast3);
    }

    public void sendBroadcast(View view){
        sendBroadcast(new Intent(BROADCAST_MY));
        sendBroadcast(new Intent(BroadcastActivity2.LOCAL_BROADCAST));
    }

    public void sendLocalBroadcast(View view){
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BroadcastActivity2.LOCAL_BROADCAST));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BroadcastActivity3.BROADCAST_MY_1));
    }
}
