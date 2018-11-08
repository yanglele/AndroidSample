package com.example.yangl.androidsample.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.yangl.androidsample.broadcast.BroadcastActivity3.BROADCAST_MY;

public class BroadcastActivity1 extends AppCompatActivity {

    TextView textView;
    FrameLayout frameLayout;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.broadcast_show)
    TextView broadcastShow;
    @BindView(R.id.local_broadcast_show)
    TextView localBroadcastShow;
    @BindView(R.id.fragment)
    FrameLayout fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast1);
        ButterKnife.bind(this);
        registerBroadcastReceiver();
        registerLocalBoradcastReceive();
        initFragment();
    }

    private void registerBroadcastReceiver(){
        IntentFilter filter = new IntentFilter(BROADCAST_MY);
        registerReceiver(receiver, filter);
    }

    private void registerLocalBoradcastReceive(){
        IntentFilter filter = new IntentFilter(BROADCAST_MY);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);
    }

    private void initFragment() {
        BroadcastFragment fragment = new BroadcastFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                fragment).commit();
    }

    public void jumpActivity2(View view) {
        startActivity(new Intent(this, BroadcastActivity2.class));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BROADCAST_MY.equals(action)) {
                broadcastShow.setText("我是全局接收器，接收全局广播：" + "i accept!");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
