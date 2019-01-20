package com.example.activity.myzhaotengshuai20190120.presenter;

import com.example.activity.myzhaotengshuai20190120.callback.MyCallBack;
import com.example.activity.myzhaotengshuai20190120.model.Model;
import com.example.activity.myzhaotengshuai20190120.model.ModelImpl;
import com.example.activity.myzhaotengshuai20190120.view.IView;

import java.util.HashMap;

public class PresenterImpl implements Presenter {

    private ModelImpl model;
    private IView iView;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model=new ModelImpl();
    }

    @Override
    public void getRequsePost(String url, HashMap<String, String> map, Class clazz) {

        model.getPostData(url, map, clazz, new MyCallBack() {
            @Override
            public void setSuccess(Object user) {
                iView.success(user);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });

    }

    @Override
    public void getRequseGet(String url, HashMap<String, String> map, Class clazz) {

        model.getGetData(url, map, clazz, new MyCallBack() {
            @Override
            public void setSuccess(Object user) {
                iView.success(user);
            }

            @Override
            public void setError(Object error) {

                iView.error(error);
            }
        });


    }
}
