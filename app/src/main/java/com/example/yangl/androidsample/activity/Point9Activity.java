package com.example.yangl.androidsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.pojo.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://www.jianshu.com/p/4432b19ec6cd
 */
public class Point9Activity extends AppCompatActivity {

    @BindView(R.id.text_view1)
    TextView textView1;
    @BindView(R.id.text_view2)
    TextView textView2;
    @BindView(R.id.text_view3)
    TextView textVIew3;
    @BindView(R.id.text_view4)
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point9);
        ButterKnife.bind(this);
        setTextView2();
        setTextView4();
    }

    private void setTextView2(){
        textView2.setPadding(50,50,50,50);
        textView2.setBackgroundResource(R.drawable.xf_list_icon_titlebggreen);
    }

    private void setTextView4(){
        textView4.setPadding(50,50,50,50);
        textView4.setBackgroundResource(R.drawable.xf_list_icon_titlebggreen2);
    }

}
