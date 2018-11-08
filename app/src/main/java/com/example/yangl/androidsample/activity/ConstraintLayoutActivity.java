package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.constraint.ChainConstraintActivity;
import com.example.yangl.androidsample.constraint.SimpleConstraintActivity;
import com.example.yangl.androidsample.constraint.SimpleConstraintActivity1;

public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
    }

    public void jumpSimpleActivity(View view){
        Intent intent = new Intent(ConstraintLayoutActivity.this, SimpleConstraintActivity1.class);
        startActivity(intent);
    }

    public void jumpChainActivity(View view){
        Intent intent = new Intent(ConstraintLayoutActivity.this, ChainConstraintActivity.class);
        startActivity(intent);
    }
}
