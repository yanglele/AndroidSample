package com.example.yangl.androidsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import java.io.UnsupportedEncodingException;

public class UTF8LengthActivity extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utf8_length);
        Log.d(TAG, "onCreate: length = "+showUTF8Length("耀"));
        Log.d(TAG, "onCreate: length = "+showUTF8Length("一"));
    }

    private int showUTF8Length(String name){
        byte[] bytes = new byte[0];
        try {
            bytes = name.getBytes("UTF-8");
            return bytes.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
