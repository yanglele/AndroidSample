package com.example.yangl.androidsample.someActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;

public class StartActivityForResultTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_for_result_test);
    }


    public void onButtonClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("name",1);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
