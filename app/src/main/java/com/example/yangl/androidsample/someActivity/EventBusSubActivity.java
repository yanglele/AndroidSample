package com.example.yangl.androidsample.someActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.activity.EventBusTestActivity;
import com.example.yangl.androidsample.model.SimpleEventBusInfo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusSubActivity extends EventBusTestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SimpleEventBusInfo info){
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(info.getInfo()+"11");
    }

    public void onButtonClick(View view){
        startActivity(new Intent(this, TestEventBusActivity.class));
    }
}
