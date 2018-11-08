package com.example.yangl.androidsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.NetTools;

public class NetStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_status);

        TextView textView = (TextView) findViewById(R.id.text);
        Boolean networkAvailable = NetTools.isNetworkAvailable(this);
        if(networkAvailable){
            textView.setText("网络可用");
        }else {
            textView.setText("网络不可用");
        }
    }
}
