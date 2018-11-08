package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
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
 * data:2018/1/2
 * version:
 * update:
 */

public class StaggeredRecyclerViewAdapter  extends RecyclerView.Adapter<ItemTextViewHolder> {

    private Context context;
    private List<String> list;

    public StaggeredRecyclerViewAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text_view_horizontal,parent,false);
        ItemTextViewHolder viewHolder = new ItemTextViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemTextViewHolder holder, int position) {
        String item = list.get(position);
        holder.bindView(item);
    }

    public void myNotify(){
        list.clear();
        for(int i=20;i<40;i++){
            list.add(i+" ");
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}

