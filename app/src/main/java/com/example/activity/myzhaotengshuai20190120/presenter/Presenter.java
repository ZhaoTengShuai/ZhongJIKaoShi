package com.example.activity.myzhaotengshuai20190120.presenter;

import com.example.activity.myzhaotengshuai20190120.callback.MyCallBack;

import java.util.HashMap;

public interface Presenter {

    void getRequsePost(String url, HashMap<String,String> map, Class clazz);
    void getRequseGet(String url, HashMap<String,String> map, Class clazz);
}
