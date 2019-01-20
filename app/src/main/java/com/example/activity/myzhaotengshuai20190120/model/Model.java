package com.example.activity.myzhaotengshuai20190120.model;

import com.example.activity.myzhaotengshuai20190120.callback.MyCallBack;

import java.util.HashMap;

public interface Model {

    void getPostData(String url, HashMap<String,String>map, Class clazz, MyCallBack callBack);
    void getGetData(String url, HashMap<String,String>map, Class clazz, MyCallBack callBack);


}
