package com.example.yangl.androidsample.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/13
 * version:
 * update:
 */

public class NetTools {

    /**
     * 判断设备网络是否可用
     * 若用户连接了一个无法使用的代理，该方法依然返回true
     * @return 网络可用返回 true, 不可用返回 false
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static Boolean isNetworkAvailable(Context context) {
        if(context == null)
            throw new IllegalArgumentException("context can not null");
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if(manager != null){
            NetworkInfo info = manager.getActiveNetworkInfo();
            return info != null && info.isConnected();
        }
        return false;
    }


    /**
     *
     * @param context
     * @return
     *  -1 : 未知
     *  TYPE_WIFI ： wifi
     *  TYPE_MOBILE ： 2G3G
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static int getNetStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager != null){
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                return ConnectivityManager.TYPE_WIFI;
            }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                return ConnectivityManager.TYPE_MOBILE;
            }
        }
        return -1;
    }

}
