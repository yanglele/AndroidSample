package com.example.yangl.androidsample;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.yangl.androidsample.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        String targetPath = this.getPackageName() + ".activity.";

        MainListActivityManager.getPackageActivities(this, targetPath);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MRecyclerViewAdapter adapter = new MRecyclerViewAdapter(this, MainListActivityManager.getInstance());
//        MRecyclerViewItemDecoration iDividerItemDecoration = new MRecyclerViewItemDecoration(this);
//        recyclerView.addItemDecoration(iDividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(1);
        recyclerView.smoothScrollToPosition(0);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager.scrollToPositionWithOffset(0,0);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
