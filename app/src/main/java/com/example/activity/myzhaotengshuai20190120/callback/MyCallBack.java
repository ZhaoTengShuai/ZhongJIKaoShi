package com.example.activity.myzhaotengshuai20190120.callback;

public interface MyCallBack<T> {

    void setSuccess(T user);
    void setError(T error);
}
