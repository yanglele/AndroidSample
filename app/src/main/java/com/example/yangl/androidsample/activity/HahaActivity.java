package com.example.yangl.androidsample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.fragment.PhotoFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HahaActivity extends AppCompatActivity {

    private final String TAG = HahaActivity.class.getSimpleName();
    @BindView(R.id.fragment_photo)
    ViewGroup fragment;
    @BindView(R.id.clearFragment)
    Button clearFragment;

    private PhotoFragment photoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haha);
        ButterKnife.bind(this);

        Activity activity = this;
        activity.getFragmentManager();

        ((HahaActivity) activity).getSupportFragmentManager();

        photoFragment = PhotoFragment.getInstance(R.drawable.image2);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_photo, photoFragment).commitAllowingStateLoss();
    }


    private void dumpHeap() {
        String path = getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        Log.d(TAG, "dumpHeap: path1=  " + path);
        ContextWrapper contextWrapper = new ContextWrapper(this);
        Log.d(TAG, "dumpHeap: path2 = " + contextWrapper.getFilesDir().getAbsolutePath() + "  " + contextWrapper.getFilesDir().getPath());
        try {
            Debug.dumpHprofData(path + "/dump.hprof");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.clearFragment)
    public void onViewClicked() {
        PhotoFragment fragment = (PhotoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_photo);
        if(fragment != null){
            fragment.setLeak();
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            System.gc();
            Log.d(TAG, "onViewClicked: fragment = "+fragment);
        }
    }
}
