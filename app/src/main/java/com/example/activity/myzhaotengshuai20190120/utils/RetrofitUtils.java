package com.example.activity.myzhaotengshuai20190120.utils;

import com.example.activity.myzhaotengshuai20190120.apiservice.MyApiService;
import com.example.activity.myzhaotengshuai20190120.contacts.Contacts;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtils {

    private MyApiService myApiService;

    private RetrofitUtils(){

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contacts.BANSE_URL)
                .client(okHttpClient)
                .build();

        myApiService=retrofit.create(MyApiService.class);
    }

    public static RetrofitUtils getInsenter(){

        return RetroHolder.retro;
    }

    private static class RetroHolder{
        private static final RetrofitUtils retro=new RetrofitUtils();
    }

    public RetrofitUtils get(String url, HashMap<String,String> map,  final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {

                if(httpListener!=null){

                    try {
                        httpListener.setSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        myApiService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return RetrofitUtils.getInsenter();
    }


    public RetrofitUtils post(String url, HashMap<String,String>map,  final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {

                if(httpListener!=null){
                    try {
                        httpListener.setSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        myApiService.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return RetrofitUtils.getInsenter();

    }

    public interface HttpListener{
        void setError(String error);
        void setSuccess(String jsonStr);

    }



}
