package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.windowManager.WindowManagerService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowWindowActivity extends AppCompatActivity {

    @BindView(R.id.show_window)
    Button showWindow;
    @BindView(R.id.close_window)
    Button closeWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_window);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.show_window, R.id.close_window})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.show_window:
                intent = new Intent(this, WindowManagerService.class);
                this.startService(intent);
                break;
            case R.id.close_window:
                intent = new Intent(this,WindowManagerService.class);
                this.stopService(intent);
                break;
                default:break;
        }
    }
}
