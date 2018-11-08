package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.model.SimpleEventBusInfo;
import com.example.yangl.androidsample.pojo.Text;
import com.example.yangl.androidsample.someActivity.EventBusSubActivity;
import com.example.yangl.androidsample.someActivity.TestEventBusActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 简单的eventbus测试类
 */
public class EventBusTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SimpleEventBusInfo info){
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(info.getInfo());
    }

    public void onButtonClick(View view){
        startActivity(new Intent(this, EventBusSubActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
