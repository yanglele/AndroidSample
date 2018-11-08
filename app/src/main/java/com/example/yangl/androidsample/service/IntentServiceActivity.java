package com.example.yangl.androidsample.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.yangl.androidsample.service.MyIntentService.ACTION_TYPE_SERVICE;
import static com.example.yangl.androidsample.service.MyIntentService.*;

public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_service_status)
    TextView tvServiceStatus;
    @BindView(R.id.tv_thread_status)
    TextView tvThreadStatus;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    private Unbinder unbinder;
    private LocalBroadcastManager localBroadcastManager;
    private MyBroadcastReceiver broadcastReceiver;

    public class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case ACTION_TYPE_SERVICE:
                    tvServiceStatus.setText("服务状态："+intent.getStringExtra("status"));
                    break;
                case ACTION_TYPE_THREAD :
                    int progress = intent.getIntExtra(PROGRESS,0);
                    tvThreadStatus.setText("线程状态：" + intent.getStringExtra("status"));
                    progressBar.setProgress(progress);
                    tvProgress.setText(progress + "%");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        unbinder = ButterKnife.bind(this);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_SERVICE);
        intentFilter.addAction(ACTION_TYPE_THREAD);
        localBroadcastManager.registerReceiver(broadcastReceiver,intentFilter);

        initView();
        initListener();
    }

    private void initView() {
        tvServiceStatus.setText("服务状态：未运行");
        tvThreadStatus.setText("线程状态：未运行");
        progressBar.setMax(100);
        progressBar.setProgress(0);
        tvProgress.setText(0 + "%");
    }

    private void initListener() {
        tvStart.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start:
                Intent startIntent = new Intent(this, MyIntentService.class);
                this.startService(startIntent);
                break;
            case R.id.tv_cancel:
                Intent stopIntent = new Intent(this, MyIntentService.class);
                this.stopService(stopIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        unbinder.unbind();
    }
}
