package com.example.yangl.androidsample.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.lauchMode.SingleTaskActivity;

import java.util.List;

public class TestSingleTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_single_task);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestSingleTaskActivity.this, SingleTaskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d("single", "onCreate: "+TestSingleTaskActivity.class.getSimpleName() + "  "+this.getTaskId());
        super.onResume();
    }
}
