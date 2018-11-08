package com.example.eventbussource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.eventbussource.EventBus.EventBus;
import com.example.eventbussource.EventBus.Subscribe;
import com.example.eventbussource.model.Info;
import com.example.eventbussource.model.NameInfo;
import com.example.eventbussource.model.SimpleInter;

public class StickyActivity extends AppCompatActivity {

    TextView stickText;
    TextView stickNameText;
    TextView stickInterfaceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        stickText = findViewById(R.id.stick_text);
        stickNameText = findViewById(R.id.stick_name_text);
        stickInterfaceText = findViewById(R.id.stick_interface_text);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void onInfo(Info info){
        stickText.setText(info.getInfo());
    }

    @Subscribe(sticky = true)
    public void onNameInfo(NameInfo info){
        stickNameText.setText(info.getInfo());
    }

    @Subscribe(sticky = true)
    public void onNameInfo(SimpleInter info){
        stickNameText.setText("接口也收到信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
