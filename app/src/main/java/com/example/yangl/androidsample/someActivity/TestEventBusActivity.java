package com.example.yangl.androidsample.someActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.model.SimpleEventBusInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestEventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);
        EventBus.getDefault().post(new SimpleEventBusInfo("hello"));
    }
}
