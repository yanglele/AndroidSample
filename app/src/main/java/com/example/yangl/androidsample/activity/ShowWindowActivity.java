package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.windowManager.WindowManagerService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowWindowActivity extends AppCompatActivity {

    private static final int OVERLAY_PERMISSION_REQ_CODE = 111;
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
                if ( Build.VERSION.SDK_INT >= 23 ) {
                    if (Settings.canDrawOverlays(this)) {
                        //有悬浮窗权限开启服务绑定 绑定权限
                         intent = new Intent(this, WindowManagerService.class);
                        startService(intent);

                    } else {
                        //没有悬浮窗权限m,去开启悬浮窗权限
                        try {
                              intent=new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // 默认有悬浮窗权限  但是 华为, 小米,oppo 等手机会有自己的一套 Android6.0 以下  
                    // 会有自己的一套悬浮窗权限管理 也需要做适配
                    intent = new Intent(this, WindowManagerService.class);
                    startService(intent);
                }
                break;
            case R.id.close_window:
                intent = new Intent(this,WindowManagerService.class);
                this.stopService(intent);
                break;
                default:break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //有悬浮窗权限开启服务绑定 绑定权限
                    Intent intent = new Intent(this, WindowManagerService.class);
                    startService(intent);
                }
            }
        }
    }
    }
