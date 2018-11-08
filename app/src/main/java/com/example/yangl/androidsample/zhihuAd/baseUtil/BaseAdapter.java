package com.example.yangl.androidsample.zhihuAd.baseUtil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/20
 * version:
 * update:
 */

public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> mList;
    protected Context mContext;

    public BaseAdapter(){}

    public BaseAdapter(Context context , List<T> list){
        mList = list;
        mContext = context;
    }

    protected void addItem(T t){
        mList.add(t);
        notifyDataSetChanged();
    }

    protected T getItem(int position){
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
