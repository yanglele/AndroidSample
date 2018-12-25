package com.example.yangl.androidsample.touchEvent.utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangl.androidsample.R;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/25
 * version:
 * update:
 */
public class ViewPagerRecyclerAdapter extends PagerAdapter {

    private List<RecyclerView> recyclerViewList;
    private Context context;

    public ViewPagerRecyclerAdapter(Context context, List<RecyclerView> listRecyclerView){
        this.context = context;
        this.recyclerViewList = listRecyclerView;
    }

    @Override
    public int getCount() {
        return recyclerViewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView = recyclerViewList.get(position);
        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(recyclerViewList.get(position));
        return;
    }
}
