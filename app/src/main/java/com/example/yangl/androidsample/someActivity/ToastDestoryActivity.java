package com.example.yangl.androidsample.someActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToastDestoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_destory);
        ButterKnife.bind(this);
        context = ToastDestoryActivity.this;
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(ToastDestoryActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        back();
    }

    public void back(){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        Log.d("", "back: finish");
        finish();
    }

}
