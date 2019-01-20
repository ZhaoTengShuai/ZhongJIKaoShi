package com.example.activity.myzhaotengshuai20190120.model;

import com.example.activity.myzhaotengshuai20190120.callback.MyCallBack;
import com.example.activity.myzhaotengshuai20190120.utils.RetrofitUtils;
import com.google.gson.Gson;


import java.util.HashMap;

public class ModelImpl implements Model {

    private MyCallBack callBack;

    @Override
    public void getPostData(String url, HashMap<String, String> map, final Class clazz, final MyCallBack callBack) {

       RetrofitUtils.getInsenter().post(url, map, new RetrofitUtils.HttpListener() {
           @Override
           public void setError(String error) {

               callBack.setError(error);
           }

           @Override
           public void setSuccess(String jsonStr) {
               Gson gson=new Gson();
               Object o = gson.fromJson(jsonStr, clazz);
               callBack.setSuccess(o);
           }
       });


    }

    @Override
    public void getGetData(String url, HashMap<String, String> map, final Class clazz, final MyCallBack callBack) {

        RetrofitUtils.getInsenter().get(url, map, new RetrofitUtils.HttpListener() {
            @Override
            public void setError(String error) {
                callBack.setError(error);
            }

            @Override
            public void setSuccess(String jsonStr) {
                Gson gson=new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                callBack.setSuccess(o);

            }
        });
    }
}
