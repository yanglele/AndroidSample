package com.example.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.Subscriber;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/4/29
 * version:
 * update:
 */
public class RxjavaAndRetrofitTest {

    public static void main(String[] args){
        getData();
    }

    public interface Service{
        @GET("/ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
        Observable<Translation> getInfo();
    }

    public static void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        service.getInfo()
        .subscribe(new Subscriber<Translation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Translation translation) {
                translation.show();
            }
        });
    }
}
