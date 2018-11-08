package com.example.yangl.androidsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/22
 * version:
 * update:
 */

public class MRecyclerViewAdapter extends RecyclerView.Adapter<MRecyclerViewAdapter.MViewHolder> {

    private List<String> list;
    private Context context;

    public MRecyclerViewAdapter(Context context , List<String> list){
        this.list = list;
        this.context = context;
    }


    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list,parent,false);
        MViewHolder viewHolder = new MViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        final String classPath = list.get(position);
        int endPoint=0;
        for(int i=0 ; i<classPath.length() ; i++){
            if('.' == classPath.charAt(i)){
                endPoint = i;
            }
        }
        final String className = classPath.substring(endPoint+1,classPath.length());
        holder.textView.setText(className);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class<?> aClass = Class.forName(classPath);
                    Intent intent = new Intent(context,aClass);
                    context.startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.main_item_text);
        }
    }
}
