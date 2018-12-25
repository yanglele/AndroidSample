package com.example.yangl.androidsample.touchEvent;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.recyclerView.RecyclerViewAdapter;
import com.example.yangl.androidsample.touchEvent.utils.ViewPagerRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_recycler_view);
        ButterKnife.bind(this);
        initViewpager();
    }

    private void initViewpager(){
        List<RecyclerView> list = new ArrayList<>();
        for(int i=1;i<=4;i++){
            list.add(getView(i));
        }

        ViewPagerRecyclerAdapter adapter  = new ViewPagerRecyclerAdapter(this,list);
        viewPager.setAdapter(adapter);
    }

    private RecyclerView getView(int tab){
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT));
        List<String> list = new ArrayList<>();
        for(int i =0;i<40;i++){
            list.add(tab+": "+i);
        }
        RecyclerView.Adapter adapter = new RecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return recyclerView;
    }
}
