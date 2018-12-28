package com.example.yangl.androidsample.touchEvent.myBehavor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.SimpleRecyclerViewAdapter;
import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewBehaviorActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_behavior);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            list.add(i+"");
        }

        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()){
//                    case MotionEvent.ACTION_MOVE:
//                        v.setY(event.getRawY());
//                }
//                return true;
//            }
//        });
    }
}
