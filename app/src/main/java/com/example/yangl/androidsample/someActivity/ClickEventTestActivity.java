package com.example.yangl.androidsample.someActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import org.greenrobot.eventbus.EventBus;

public class ClickEventTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_event_test);
    }

    public void onClickEventTest(View view){
        Toast.makeText(this,"come",Toast.LENGTH_SHORT).show();
    }
}
