
package com.example.yangl.androidsample.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;

public class DebugUtil {
    private static volatile DebugUtil _instance;
    private static boolean _isDebugBuild;
    private final static int DEBUG_SIGNATURE_HASH = -545290802;

    private final String LOG_TAG;
    private static long oldTime = System.currentTimeMillis();

    /** 初始化DEBUG，建议Application启动调用，否则直接调用static方法可能会有异常 */
    public static DebugUtil initialize(Context context) {
        if (_instance == null) {
            synchronized (DebugUtil.class) {
                if (_instance == null)
                    _instance = new DebugUtil(context);
            }
        }

        return _instance;
    }

    private DebugUtil(Context context) {
        LOG_TAG = DebugUtil.class.getName();
        oldTime = System.currentTimeMillis();
        _isDebugBuild = isDebugBuild(context);
    }

    private boolean isDebugBuild(Context context) {
        try {
            Signature[] sigs = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            /*
             * 单元测试环境下sigs 为空
             */
            if (sigs == null) {
                return false;
            }
            for (int i = 0; i < sigs.length; i++) {
                Log.v(LOG_TAG, "sign[" + i + "] hash: " + sigs[i].hashCode());
                if (sigs[i].hashCode() == DEBUG_SIGNATURE_HASH) {
                    Log.d(LOG_TAG, "This is a debug build!");
                    return true;
                }
                Log.v("thinking", " hashCode = " + sigs[i].hashCode());
            }
        } catch (NameNotFoundException e) {
            Log.e(getClass().getSimpleName(), e.getClass().getSimpleName(), e);
        }

        return false;
    }

    /** 获取APP是否为DEBUG模式，使用该方法前，确认已在Application中初始化过该类 */
    public static boolean getDebugModel() {
        return _isDebugBuild;
    }

    public static void timePoint(String text) {
        Long currentTime = System.currentTimeMillis();
        Long durationTime = currentTime - oldTime;
        v("mydebug", text + " durationTime:" + durationTime);
        oldTime = currentTime;
    }

    public static void d(String text) {
        if (_isDebugBuild)
            Log.d("anjuke", text);
    }

    public static void w(String text) {
        if (_isDebugBuild)
            Log.w("anjuke", text);
    }

    public static void v(String text) {
        if (_isDebugBuild)
            Log.v("anjuke", text);
    }

    public static void i(String text) {
        if (_isDebugBuild)
            Log.i("anjuke", text);
    }

    public static void e(String text) {
        if (_isDebugBuild)
            Log.e("anjuke", text);
    }

    public static void d(String tag, String text) {
        if (_isDebugBuild)
            Log.d(tag, text);
    }

    public static void v(String tag, String text) {
        if (_isDebugBuild)
            Log.v(tag, text);
    }

    public static void i(String tag, String text) {
        if (_isDebugBuild)
            Log.i(tag, text);
    }

    public static void e(String tag, String text) {
        if (_isDebugBuild)
            Log.e(tag, text);
    }

    public static void w(String tag, String text) {
        if (_isDebugBuild)
            Log.w(tag, text);
    }

    public static void setDebug() {

    }

}
