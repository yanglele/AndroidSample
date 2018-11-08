package com.example.yangl.androidsample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/6/17
 * version:
 * update:
 */

public class ViewPager2Adapter extends PagerAdapter {

    private List<Integer> list = new ArrayList<>();
    private Context context;

    public ViewPager2Adapter(List<Integer> imageResouceList ,Context context){
        list.addAll(imageResouceList);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("11111", "instantiateItem: "+position);
        View view = LayoutInflater.from(context).inflate(R.layout.simple_image_view,container,false);
        ((ImageView)view.findViewById(R.id.image_view)).setImageResource(list.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.d("11111", "destroyItem: "+position);
    }
}
