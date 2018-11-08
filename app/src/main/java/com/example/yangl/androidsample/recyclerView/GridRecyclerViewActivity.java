package com.example.yangl.androidsample.recyclerView;

import android.os.Bundle;
import android.support.annotation.Px;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.grid_recycler_view)
    RecyclerView gridRecyclerView;

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycler_view);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        for(int i=0;i<200;i++){
            list.add(i+" ");
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().commitAllowingStateLoss();

        gridRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        @Px int verticalDividerWidth = (UISizeUtils.getScreenWidth(this) - UISizeUtils.dip2px(this,60*4))/3;
        DividerGridLayout dividerGridLayout = new DividerGridLayout(3,5,5);
        gridRecyclerView.addItemDecoration(dividerGridLayout);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,list);
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(4,24,false);
//        gridRecyclerView.addItemDecoration(gridItemDecoration);
        gridRecyclerView.setAdapter(adapter);
    }
}
