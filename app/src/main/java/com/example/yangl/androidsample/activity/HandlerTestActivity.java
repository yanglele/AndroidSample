package com.example.yangl.androidsample.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import java.security.Principal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandlerTestActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 111){
                button.setText("come");
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 111){
                Toast.makeText(HandlerTestActivity.this,"1",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        ButterKnife.bind(this);

        button.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 111;
                        handler.sendMessage(message);

                        Message message2 = new Message();
                        message2.what = 111;
                        handler2.sendMessage(message2);
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(HandlerTestActivity.this,"1",Toast.LENGTH_SHORT).show();
//                            }
//                        });
                    }
                }).start();
            }
        });
    }

    class LooperThread extends Thread{

        private Handler handler;

        @Override
        public void run() {
            Looper.prepare();

            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };

            Looper.loop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(111);
        handler2.removeMessages(111);
    }
}
