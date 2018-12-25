package com.example.yangl.androidsample.touchEvent.myCoordinatorLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.example.yangl.androidsample.R;

public class MyCoordinatorActivity extends AppCompatActivity {

    private NestedListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coordinator);
        mListView = (NestedListView) findViewById(R.id.list_view);
        String[] arr = new String[100];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + "";
        }
        mListView.setAdapter(new ArrayAdapter(this, R.layout.list_item_layout, R.id.text_name, arr));
    }
}
