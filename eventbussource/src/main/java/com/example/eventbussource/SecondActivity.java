package com.example.eventbussource;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eventbussource.EventBus.EventBus;
import com.example.eventbussource.model.Info;
import com.example.eventbussource.model.NameInfo;

public class SecondActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = findViewById(R.id.edit_view);
        button = findViewById(R.id.button);
    }

    public void onButtonClick(View view){
        String string = editText.getText().toString();
        NameInfo info = new NameInfo(string,"yl");
        EventBus.getDefault().post(info);
        finish();
    }

    public void onStickyButtonClick(View view){
//        String string = editText.getText().toString();
        Info info = new Info("yl");
        EventBus.getDefault().postSticky(info);
        Info info1 = new Info("yll");
        EventBus.getDefault().postSticky(info1);
        startActivity(new Intent(this,StickyActivity.class));
    }

    public void onBackSend(View view){
        String string = editText.getText().toString();
        final Info info = new Info(string);
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(info);
            }
        }).start();
        finish();
    }
}
