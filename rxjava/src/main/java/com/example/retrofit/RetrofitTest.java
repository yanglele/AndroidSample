package com.example.retrofit;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/4/28
 * version:
 * update:
 */
public class RetrofitTest {
    public static void main(String[] args){

        getInfo();
    }

    //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world

    interface Service{
        @GET("/ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
        Call<Translation> getString();
    }

    public static void getInfo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Translation> call = service.getString();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
               response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("error");
            }
        });
//        ProxyUtil.generateClassFile(service.getClass(),"RetrofitProxy");

    }


}
