package com.example.yangl.androidsample.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.yangl.androidsample.fragment.PhotoFragment;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/4/19
 * version:
 * update:
 */

public class MyViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Integer> lists;
    private Context context;

    public MyViewPagerFragmentAdapter(FragmentManager fm , List<Integer> lists , Context context) {
        super(fm);
        this.lists = lists;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("aaaaa", "getItem: "+position);
        return new PhotoFragment(lists.get(position),context);
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }


}
