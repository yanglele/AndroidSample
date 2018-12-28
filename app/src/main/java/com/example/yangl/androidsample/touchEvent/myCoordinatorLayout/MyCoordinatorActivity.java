package com.example.yangl.androidsample.touchEvent.myCoordinatorLayout;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.SimpleRecyclerViewAdapter;
import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

public class MyCoordinatorActivity extends AppCompatActivity {

    private NestRecyclerView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coordinator);
        mListView = (NestRecyclerView) findViewById(R.id.list_view);
        String[] arr = new String[100];

        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + "";
            list.add(i+"");
        }

        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(new SimpleRecyclerViewAdapter(this,list));
//        mListView.setAdapter(new ArrayAdapter(this, R.layout.list_item_layout, R.id.text_name, arr));
    }
}
