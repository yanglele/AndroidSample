package com.example.yangl.androidsample.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.fragment.CommitFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentLiftActivity extends AppCompatActivity {

    private final String TAG = "FragmentLiftActivity";

    @BindView(R.id.fragment)
    FrameLayout fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_lift);
        ButterKnife.bind(this);
        addFragment();
        Log.d(TAG, "onCreate: "+this);
    }

    private void addFragment(){
        CommitFragment fragment = (CommitFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(fragment == null){
            fragment = CommitFragment.newInstance("yl");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG+"1", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    public void onButtonClick(View view){
        Intent intent = new Intent(this,AlterDialogActivity.class);
        startActivity(intent);
    }
}
