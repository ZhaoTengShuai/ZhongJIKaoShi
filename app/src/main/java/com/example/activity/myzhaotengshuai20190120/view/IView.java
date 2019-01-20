package com.example.activity.myzhaotengshuai20190120.view;

public interface IView<T> {

    void success(T data);
    void error(T error);

}
