package com.example.yangl.androidsample.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.adapter.MyViewPagerFragmentAdapter;
import com.example.yangl.androidsample.adapter.ViewPager2Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPager2 extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager(){
        List<Integer> lists = new ArrayList<>();
        lists.add(R.drawable.image3);
        lists.add(R.drawable.image3);
        lists.add(R.drawable.image3);
        lists.add(R.drawable.image3);
        lists.add(R.drawable.image3);
        lists.add(R.drawable.image3);
        ViewPager2Adapter fragmentAdapter = new ViewPager2Adapter(lists,this);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("11111", "onPageSelected: childCount = "+viewPager.getChildCount());
                Log.d("11111", "onPageSelected: position = "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("11111", "onPageScrollStateChanged: = ");
            }
        });
    }
}
