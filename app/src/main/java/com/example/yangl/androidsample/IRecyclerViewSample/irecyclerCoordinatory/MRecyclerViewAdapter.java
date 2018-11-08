package com.example.yangl.androidsample.IRecyclerViewSample.irecyclerCoordinatory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseAdapter;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/26
 * version:
 * update:
 */

public class MRecyclerViewAdapter extends BaseAdapter<Object,BaseViewHolder> {

    private final int NORMAL_VIEW_HOLDER= R.layout.simple_text_view;

    public MRecyclerViewAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(NORMAL_VIEW_HOLDER,parent,false);
        return new MRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindView(mContext,mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
