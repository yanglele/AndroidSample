package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class RandomRecyclerViewAdapter extends RecyclerView.Adapter<ItemTextViewHolder> {

    private Context context;
    private List<String> list;

    public RandomRecyclerViewAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text_view_random,parent,false);
        ItemTextViewHolder viewHolder = new ItemTextViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemTextViewHolder holder, final int position) {
        String item = list.get(position);
        holder.bindView(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,position+"  num = "+list.get(position),Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,position+"  num = "+list.get(holder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
