package com.example.yangl.androidsample.someActivity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.fragment.EmptyFragment;
import com.example.yangl.androidsample.fragment.PhotoFragment;
import com.example.yangl.androidsample.view.MyButton;

import java.net.URI;
import java.net.URL;

public class ViewLifeCycleActivity extends AppCompatActivity {

    private String TAG = "ViewLifeCycleActivity " + "view_life";

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_life_cycle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new EmptyFragment()).commit();

        Uri uri = Uri.parse("http://www.baidu.com");
        Uri.Builder builder = uri.buildUpon();
        builder.appendPath("123123").appendPath("");
        builder.appendQueryParameter("name", "yl");
        Log.d(TAG, "onCreate: " + builder.toString() + "path = " + uri.getPath());

        Uri uri1 = Uri.parse("http://www.baidu.com?come=1");
        Uri.Builder builder1 = uri1.buildUpon().appendQueryParameter("name", "yl");
        Log.d(TAG, "onCreate: " + uri1.toString());
        Log.d(TAG, "onCreate: " + builder1.toString());
    }
}
