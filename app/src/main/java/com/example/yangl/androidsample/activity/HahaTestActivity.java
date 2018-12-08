package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.testActivity.ObjectLeakActivity;
import com.example.yangl.androidsample.tools.StorageUtil;
import com.squareup.haha.perflib.ClassObj;
import com.squareup.haha.perflib.HprofParser;
import com.squareup.haha.perflib.Snapshot;
import com.squareup.haha.perflib.io.HprofBuffer;
import com.squareup.haha.perflib.io.MemoryMappedFileBuffer;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HahaTestActivity extends AppCompatActivity {

    @BindView(R.id.jump)
    Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haha_test);
        ButterKnife.bind(this);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(HahaTestActivity.this, ObjectLeakActivity.class),0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        try {
            file = StorageUtil.createFile("/dump1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Debug.dumpHprofData(file.getAbsolutePath());
            HprofBuffer buffer = new MemoryMappedFileBuffer(file);
            HprofParser parser = new HprofParser(buffer);
            Snapshot snapshot = parser.parse();
            ClassObj classObj = snapshot.findClass("com.example.yangl.androidsample.testActivity.ObjectLeakActivity");
            Log.d("leak", "onActivityResult: "+classObj);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
