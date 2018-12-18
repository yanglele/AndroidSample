package com.example.yangl.androidsample.testActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

public class ObjectLeakActivity extends AppCompatActivity {

    public static List<Context> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_leak);
        list.add(this);

    }
}
