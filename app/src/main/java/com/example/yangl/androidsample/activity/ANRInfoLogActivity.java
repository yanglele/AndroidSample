package com.example.yangl.androidsample.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.DropboxLogcatExporter;

import java.io.IOException;

public class ANRInfoLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anrinfo_log);
        testDropboxExporter(null);
    }

    public void testDropboxExporter(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            testDropbox();
        }
    }

    private void testDropbox() {
        HandlerThread handlerThread = new HandlerThread("DropboxExporter");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String destPath = DropboxLogcatExporter.getInstance().output(
                        ANRInfoLogActivity.this, Environment.getExternalStorageDirectory().getPath() + "/dropbox.log", 800 * 1024);
                Log.d("whh", "dropbox output success: " + destPath);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (verifyPermissions(grantResults)) {
                testDropbox();
            } else {
                Toast.makeText(ANRInfoLogActivity.this, "获取读写权限失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
