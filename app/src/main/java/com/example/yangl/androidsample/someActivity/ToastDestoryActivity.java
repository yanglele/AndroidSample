package com.example.yangl.androidsample.someActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToastDestoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_destory);
        ButterKnife.bind(this);
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                finish();
                Toast.makeText(ToastDestoryActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
