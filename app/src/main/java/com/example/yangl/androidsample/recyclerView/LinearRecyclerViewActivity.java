package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.IDividerItemDecoration;
import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(i+" ");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
        IDividerItemDecoration iDividerItemDecoration = new IDividerItemDecoration(this);
        recyclerView.addItemDecoration(iDividerItemDecoration);
    }
}
