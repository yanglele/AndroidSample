package com.example.yangl.androidsample.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import static com.example.yangl.androidsample.broadcast.BroadcastActivity3.BROADCAST_MY;

/**
 * Desc:发送的广播在activity栈中的activity、fragment均能收到该广播
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/5/21
 * version:
 * update:
 */

public class BroadcastFragment extends Fragment {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BROADCAST_MY.equals(action)){
                textView.setText("fragment中的全局广播也收到了"+"i accept too!");
            }
        }
    };

    private TextView textView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        IntentFilter filter = new IntentFilter(BROADCAST_MY);
        context.registerReceiver(receiver,filter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        return view;
    }


}
