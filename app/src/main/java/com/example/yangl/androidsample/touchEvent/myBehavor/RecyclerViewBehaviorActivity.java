package com.example.yangl.androidsample.touchEvent.myBehavor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.SimpleRecyclerViewAdapter;
import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://www.jianshu.com/p/b987fad8fcb4
 */
public class RecyclerViewBehaviorActivity extends AppCompatActivity {


    @BindView(R.id.header)
    TextView header;
    @BindView(R.id.my_list)
    RecyclerView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_behavior);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i + "");
        }

        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(this, list);
        myList.setAdapter(adapter);
        myList.setLayoutManager(new LinearLayoutManager(this));
    }
}
