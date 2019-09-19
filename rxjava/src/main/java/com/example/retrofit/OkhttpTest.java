package com.example.retrofit;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/4/29
 * version:
 * update:
 */
public class OkhttpTest {

    public static void main(String[] args){
        try {
            okhttpSynGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void okhttpSynGet() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if(!response.isSuccessful()){
            throw  new IOException(response+"");
        }

        System.out.println(response.body().string());

    }

    public static void okhttpAsynGet() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });

    }
}
