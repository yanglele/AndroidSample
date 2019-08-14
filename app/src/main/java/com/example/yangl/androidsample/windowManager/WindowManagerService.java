package com.example.yangl.androidsample.windowManager;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/8/9
 * version:
 * update:
 */
public class WindowManagerService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private WindowManager.LayoutParams wmParams;
    private WindowManager mWindowManager;
    private View mWindowView;
    private TextView mPercentTv;

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        initWindowParams();
        initView();
        addWindowView2Window();
//        initClick();
    }

    private void initWindowParams() {
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();
        // 更多type：https://developer.android.com/reference/android/view/WindowManager.LayoutParams.html#TYPE_PHONE
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.TRANSLUCENT;
        // 更多falgs:https://developer.android.com/reference/android/view/WindowManager.LayoutParams.html#FLAG_NOT_FOCUSABLE
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    private void initView() {
        mWindowView = LayoutInflater.from(getApplication()).inflate(R.layout.layout_window, null);
        mPercentTv = (TextView) mWindowView.findViewById(R.id.percentTv);
    }

    private void addWindowView2Window() {
        mWindowManager.addView(mWindowView, wmParams);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWindowView != null) {
            //移除悬浮窗口
            Log.i(TAG, "removeView");
            mWindowManager.removeView(mWindowView);
        }
        Log.i(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
