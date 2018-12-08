package com.example.eventbussource;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventbussource.EventBus.EventBus;
import com.example.eventbussource.EventBus.Subscribe;
import com.example.eventbussource.EventBus.ThreadMode;
import com.example.eventbussource.model.Info;
import com.example.eventbussource.model.NameInfo;
import com.example.eventbussource.model.SimpleInter;

public class MainActivity extends AppCompatActivity {


    private TextView showView;
    private TextView showView2;
    private TextView showView3;
    private Button button;
    private Button sendLot;

    private TextView showView4;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showView = findViewById(R.id.show_view);
        showView2 = findViewById(R.id.show_view_2);
        showView3 = findViewById(R.id.show_view_3);
        button = findViewById(R.id.button);
        sendLot = findViewById(R.id.send_lot);
        showView4 = findViewById(R.id.show_view_4);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN ,priority = 0)
    public void setTextView(Info info) throws InterruptedException {
        showView.setText(info.getInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextNameView(NameInfo info){
        showView2.setText(info.getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setInterfaceNameView(SimpleInter info){
        showView3.setText("相应接口也会受到信息");
    }

    public void onButtonClick(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void onSendClick(View view){
//        for(int i = 0;i<10;i++){
//            String s = "!"+i;
//            EventBus.getDefault().post(new Info(s));
//        }
        EventBus.getDefault().post(new Info("1"));

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND ,sticky = true)
    public void setTextNameView4(Info info) throws InterruptedException {
        Thread.sleep(500);
        Log.d("444", "setTextNameView4: "+info.getInfo());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void setTextNameView5(Info info) throws InterruptedException {
        Thread.sleep(500);
        Log.d("555", "setTextNameView4: "+info.getInfo());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void setTextNameView6(Info info) throws InterruptedException {
        Thread.sleep(500);
        Log.d("666", "setTextNameView4: "+info.getInfo());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
