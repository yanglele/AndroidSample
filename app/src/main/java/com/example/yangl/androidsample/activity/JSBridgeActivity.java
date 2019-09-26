package com.example.yangl.androidsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

public class JSBridgeActivity extends AppCompatActivity {

    TextView callJs;
    TextView showJsCallback;
    BridgeWebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsbridge);
        initView();
        callJs();
        registerInJs();
    }

    private void initView() {
        callJs = (TextView) findViewById(R.id.tv_androidcalljs);
        showJsCallback = (TextView) findViewById(R.id.tv_showmsg);

        webview = (BridgeWebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //与js交互必须设置
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/html.html");
    }

    private void callJs(){
        callJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 第一个参数是H5页面注册的一个名为"functionInJs"的方法
                // 第二个参数是客户端本地传给H5的字符串
                // 第三个参数是实现回调接口的匿名内部类
                webview.callHandler("functionInJs", "Android传递给js的数据", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        showJsCallback.setText(data);
                    }
                });
            }
        });
    }

    private void registerInJs() {

        // 第一个参数在本地注册一个叫"submitFromWeb"的方法供H5调用，
        // 第二个参数是实现了BridgeHandler接口的匿名类用来回调。
        webview.registerHandler("functionInAndroid", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                showJsCallback.setText("js调用了Android  "+data);
                //返回给html
                function.onCallBack("Android回传给js的数据");
            }
        });
    }
}
