package com.example.yangl.androidsample.uiTools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import com.example.yangl.androidsample.ConstantStirng;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/22
 * version:
 * update:
 */

public class UISizeUtils {


    //获取状态栏高度
    public static int getStatusBarHeight(Activity activity){
        int statusBarHeight = 0;
        try {
            if(activity != null){
                int resourceId = activity.getResources().getIdentifier("status_bar_height","dimen","android");
                if(resourceId > 0){
                    statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d(ConstantStirng.TAG, "getStatusBarHeight: "+statusBarHeight);
        return statusBarHeight;
    }

    /**
     *
     * @param context
     * @return
     */
     public static int getActionBarHeight(@NonNull Context context){
        if(context == null){
            throw new IllegalArgumentException("context can not be null");
        }
        TypedValue tv = new TypedValue();
        int actionBarHeight=0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, @Px int pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    //屏幕高度
    public static int getScreenHeight(Activity activity){
        Point p = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(p);
        return p.y;
    }

    //屏幕宽度
    public static int getScreenWidth(Activity activity){
        Point p = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(p);
        return p.x;
    }

    //content高度
    public static int getContentViewHeight(Activity activity){
        return activity.findViewById(android.R.id.content).getHeight();
    }


    public static int getNavigationBarHeight(Context context) {
        try {
            boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (!hasMenuKey && !hasBackKey) {
                Resources resources = context.getResources();
                int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
                //获取NavigationBar的高度
                return resources.getDimensionPixelSize(resourceId);
            } else {
                return 0;
            }
        } catch (Exception e) {
            Log.e("UIUtil", e.getClass().getSimpleName(), e);
            return 0;
        }
    }

}
