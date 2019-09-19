package com.example.yangl.androidsample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandlerActivity extends AppCompatActivity {

    @BindView(R.id.send)
    Button send;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.text_view)
    TextView textView;

    private int count = 1;

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView.setText(String.valueOf(++count));
                    Log.d("123", "handleMessage: "+count);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);
    }

    public void begin(View view) {
        handler.sendEmptyMessageDelayed(1,3000);
    }

    public void stop(View view) {
        handler.removeMessages(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("111", "onDestroy: ");
    }
}
