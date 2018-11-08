package com.example.yangl.androidsample.recyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RandomStaggeredRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.random_recycler_view)
    RecyclerView randomRecyclerView;
    List<String> list = new ArrayList<>();
    RandomRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendom_staggered_recycler);
        ButterKnife.bind(this);

        for (int i = 0; i < 5; i++) {
            list.add(i + " ");
        }

        randomRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
//        randomRecyclerView.addItemDecoration(new DividerGridLayout(this));
        adapter = new RandomRecyclerViewAdapter(this, list);
        randomRecyclerView.setAdapter(adapter);
        randomRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @OnClick({R.id.add_btn, R.id.delete_btn ,R.id.test_btn})
    public void onViewClicked(View view) {
        for(int i = 0;i<randomRecyclerView.getChildCount();i++){
            Log.d("111", "onViewClicked: "+randomRecyclerView.getChildAt(i));
        }
        switch (view.getId()) {
            case R.id.add_btn:
                list.add(1,"添加的item");
//                list.set(2,"100");
//                adapter.notifyItemInserted(1);
                adapter.notifyDataSetChanged();
                break;
            case R.id.delete_btn:
                list.remove(0);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
