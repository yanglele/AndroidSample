package com.example.yangl.androidsample.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.yangl.androidsample.R;

import static com.example.yangl.androidsample.uiTools.UISizeUtils.getNavigationBarHeight;

/**
 * 修改navigationBar颜色
 */
public class NavigationBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);
        setNavigationBarColor(this, ContextCompat.getColor(this,R.color.mTransparency));
        ViewGroup viewGroup = new LinearLayoutCompat(NavigationBarActivity.this);
        viewGroup.setBackgroundResource(R.drawable.icon1);
    }

    public static void setNavigationBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上可以直接设置 navigation颜色 在styles.xml(>=V21中设置也可以)
            activity.getWindow().setNavigationBarColor(color);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // android:clipToPadding="false"
            //android:fitsSystemWindows="true"
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View mNavigationBar = getNavigationBarView(activity);
            FrameLayout.LayoutParams params;
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationBarHeight(activity));
            params.gravity = Gravity.BOTTOM;
            mNavigationBar.setLayoutParams(params);
            mNavigationBar.setBackgroundColor(color);
            decorView.addView(mNavigationBar);
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
            if(viewGroup.getChildAt(0) != null){
                viewGroup.getChildAt(0).setFitsSystemWindows(true);
            }
        }else{
            //4.4以下无法设置navigationbar颜色
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

    }

    public static View getNavigationBarView(Activity activity) {

        View view = new View(activity);
        return view;
    }

    public static int getNavigationBarHeight(Context context) {
        int height = 0;
        int id = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0) {
            height = context.getResources().getDimensionPixelSize(id);
        }
        return height;
    }
}
