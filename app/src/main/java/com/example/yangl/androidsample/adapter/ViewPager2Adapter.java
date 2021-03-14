package com.example.yangl.androidsample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        View view = LayoutInflater.from(context).inflate(R.layout.simple_image_view_layout,container,false);
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView textView = view.findViewById(R.id.textView);
        imageView.setImageResource(list.get(position));
        textView.setText(String.valueOf(list.get(position)));
        container.addView(view);
        imageView.setOnClickListener(v -> {
//            list.remove(position);
            list.add(position,R.drawable.image7);
            notifyDataSetChanged();
            Toast.makeText(context,"position " + position + " has been delete!" ,Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.d("11111", "destroyItem: "+position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
