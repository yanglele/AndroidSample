package com.example.yangl.androidsample.zhihuAd.baseUtilImpl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangl.androidsample.ConstantStirng;
import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.zhihuAd.dataBean.TextViewHolder;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseAdapter;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;
import com.example.yangl.androidsample.zhihuAd.dataBean.ImageInfo;
import com.example.yangl.androidsample.zhihuAd.dataBean.TextInfo;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/20
 * version:
 * update:
 */

public class MyRecyclerViewAdapter extends BaseAdapter<Object , BaseViewHolder> {

    private final int NORMAL_ITEM_VIEW= R.layout.text_item;
    private final int IMAGE_ITEM_VIEW=R.layout.image_item;
    private int count = 0;

    public MyRecyclerViewAdapter(Context context , List list){
        super(context,list);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        count++;
        Log.d(ConstantStirng.TAG, "onCreateViewHolder:"+count);
        View view = LayoutInflater.from(mContext).inflate(viewType,parent,false);
       if(NORMAL_ITEM_VIEW == viewType){
           return new TextViewHolder(view);
       }else if (IMAGE_ITEM_VIEW == viewType){
           return new ImageViewHolder(view);
       }
       return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindView(mContext,mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mList.get(position);
        if(item instanceof TextInfo){
            return NORMAL_ITEM_VIEW;
        }else if(item instanceof ImageInfo){
            return IMAGE_ITEM_VIEW;
        }
        return -1;
    }
}
