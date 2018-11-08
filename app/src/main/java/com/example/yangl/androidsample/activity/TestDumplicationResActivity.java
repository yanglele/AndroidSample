package com.example.yangl.androidsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.contentObserver.IViewHolder;

public class TestDumplicationResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dumplication_res);
        TextView textView = (TextView) findViewById(R.id.test1);
        IViewHolder iViewHolder = new IViewHolder(textView) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }
}
