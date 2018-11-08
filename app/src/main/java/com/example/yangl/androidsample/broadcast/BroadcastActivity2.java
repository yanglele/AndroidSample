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
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.yangl.androidsample.broadcast.BroadcastActivity3.BROADCAST_MY_1;

public class BroadcastActivity2 extends AppCompatActivity {

    public static final String LOCAL_BROADCAST = "local_broadcast";
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.local_broadcast_show)
    TextView localBroadcastShow;
    @BindView(R.id.broadcast_show)
    TextView broadcastShow;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (LOCAL_BROADCAST.equals(action)) {
                localBroadcastShow.setText("i accept local broadcast!");
            }
        }
    };

    private BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
           if(BROADCAST_MY_1.equals(action)){
                broadcastShow.setText("我竟然收到了广播！");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast2);
        ButterKnife.bind(this);

        IntentFilter filter = new IntentFilter(LOCAL_BROADCAST);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(BROADCAST_MY_1);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver1,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver1);
        super.onStop();
    }

    public void jumpActivity3(View view) {
        startActivity(new Intent(this, BroadcastActivity3.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
