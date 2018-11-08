package com.example.yangl.androidsample.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import java.util.Random;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/2
 * version:
 * update:
 */

public class ItemTextViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private int count;

    public ItemTextViewHolder(View itemView) {
        super(itemView);
//        textView = (TextView) itemView.findViewById(R.id.text);
    }

    public void bindView(String string){
//        textView.setText(string);
//        count++;
//        ViewGroup.LayoutParams params = textView.getLayoutParams();
//        Random random = new Random();
//        int anInt = random.nextInt(200)+50;
//        params.height = anInt;
//        textView.setLayoutParams(params);
    }

}
