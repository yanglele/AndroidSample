package com.example.yangl.androidsample.lauchMode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.activity.TestSingleTaskActivity;

public class SingleTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        Log.d("single", "onCreate: "+SingleTaskActivity.class.getSimpleName() + "  "+this.getTaskId());

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this,EmptyActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d("single", "onCreate: "+SingleTaskActivity.class.getSimpleName() + "  "+this.getTaskId());
        super.onResume();
    }
}
