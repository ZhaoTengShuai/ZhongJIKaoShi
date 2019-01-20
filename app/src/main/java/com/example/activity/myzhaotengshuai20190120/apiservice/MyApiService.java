package com.example.activity.myzhaotengshuai20190120.apiservice;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface MyApiService {

    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap HashMap<String,String>map);
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap HashMap<String,String>map);
}
