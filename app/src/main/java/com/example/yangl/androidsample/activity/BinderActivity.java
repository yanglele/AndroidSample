package com.example.yangl.androidsample.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.binderservice.BinderService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BinderActivity extends AppCompatActivity {

    private final String TAG = BinderActivity.class.getSimpleName();

    private IBinder iBinder;

    private int anInt=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        ButterKnife.bind(this);
        final int[] b = {1};
        new Thread(new Runnable() {
            @Override
            public void run() {
                anInt++;
                b[0]++;
                Log.d(TAG, "run: "+anInt);
            }
        }).start();
    }

    @OnClick(R.id.bind_service)
    public void onViewClicked() {
        bindService(new Intent(this, BinderService.class), new BindConnection(), BIND_AUTO_CREATE);
    }

    private class BindConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinder = service;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            data.writeInterfaceToken("reporter");
            data.writeString("this is a test string.");
            data.writeInt(12);
            try {
                iBinder.transact(BinderService.REPORT_CODE, data, reply, 0);
                reply.enforceInterface("reporter");
                int result = reply.readInt();
                Toast.makeText(BinderActivity.this, result + "", Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            data.recycle();
            reply.recycle();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBinder = null;
        }
    }
}
