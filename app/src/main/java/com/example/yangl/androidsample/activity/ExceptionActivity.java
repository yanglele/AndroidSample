package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.yangl.androidsample.MainActivity;
import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.fragment.CommitFragment;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExceptionActivity extends AppCompatActivity {

    private final String TAG = ExceptionActivity.class.getSimpleName();

    @BindView(R.id.on_save_instance_state)
    Button onSaveInstanceState;
    @BindView(R.id.save_instance_fragment)
    FrameLayout saveInstanceFragment;
    @BindView(R.id.jump)
    Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        ButterKnife.bind(this);
        addFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void addFragment() {
        CommitFragment commitFragment = CommitFragment.newInstance("杨乐");
        getSupportFragmentManager().beginTransaction().replace(R.id.save_instance_fragment,commitFragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null){
            Set<String> set = outState.keySet();
            for(String string : set){
                Log.d(TAG, "onSaveInstanceState: "+string);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            Set<String> set = savedInstanceState.keySet();
            for(String string : set){
                Log.d(TAG, "onRestoreInstanceState: "+string);
            }
        }
    }

    public void jump(View view){
        Intent intent = new Intent(ExceptionActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
