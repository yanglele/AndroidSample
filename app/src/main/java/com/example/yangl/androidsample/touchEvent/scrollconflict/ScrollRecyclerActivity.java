package com.example.yangl.androidsample.touchEvent.scrollconflict;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.SimpleRecyclerViewAdapter;
import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_recycler);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView(){
        List<String> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add(String.valueOf(i));
        }
        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
