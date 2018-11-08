package com.example.yangl.androidsample.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

public class SystemInfoActivity extends AppCompatActivity {

    private final String TAG = SystemInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
    }

    private String getDeviceId(Context context){
        String deviceId = "";
        try {
            TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                deviceId = mTelephonyMgr.getDeviceId();
                if (deviceId == null || deviceId.length() < 8 || deviceId.startsWith("00000000")) {
                    deviceId = "";
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "getDeviceId: "+e.getMessage());
        }

        return deviceId;
    }

    public void onDeviceIdClick(View view){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1001);
        }else {
            Toast.makeText(this,getDeviceId(this),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,getDeviceId(this),Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }
}
