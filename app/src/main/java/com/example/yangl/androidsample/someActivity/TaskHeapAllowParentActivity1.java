package com.example.yangl.androidsample.someActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;

public class TaskHeapAllowParentActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_heap_allow_parent1);
    }

    public void onButtonClick(View view){
        startActivity(new Intent(TaskHeapAllowParentActivity1.this,TaskHeapAllowParentActivity2.class));
    }
}
