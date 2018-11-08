package com.example.yangl.androidsample.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;


public class AlterDialogActivity extends AppCompatActivity {

    private final String TAG = "AlterDialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_dialog);
        Log.d(TAG, "onCreate: ");
    }

    public void onButton1Click(View view){
        final String[] items = new String[]{"hello","hello","hello"};
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("显示Item对话框").setIcon(R.drawable.icon1)
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlterDialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.show();
    }

    public void onCircle(View view){
        for(int i = 0;i< 100 ;i++){

            startActivity(new Intent(this,FragmentLiftActivity.class));
        }
    }
}
