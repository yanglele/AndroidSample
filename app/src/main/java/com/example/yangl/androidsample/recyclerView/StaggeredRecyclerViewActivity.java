package com.example.yangl.androidsample.recyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggeredRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.straggered_recycler_view)
    RecyclerView straggeredRecyclerView;
    StaggeredRecyclerViewAdapter adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_recycler_view);
        ButterKnife.bind(this);

        list.clear();
        for(int i=0;i<20;i++){
            list.add(i+" ");
        }
        straggeredRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(15, StaggeredGridLayoutManager.HORIZONTAL));
//        straggeredRecyclerView.addItemDecoration(new DividerGridLayout(this));
        adapter = new StaggeredRecyclerViewAdapter(this,list);
        straggeredRecyclerView.setAdapter(adapter);

    }


    public void onButtonClick(View view){
      adapter.myNotify();
    }
}
