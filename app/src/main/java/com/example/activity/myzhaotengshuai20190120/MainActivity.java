package com.example.activity.myzhaotengshuai20190120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.activity.myzhaotengshuai20190120.adapter.MyShouAdapter;
import com.example.activity.myzhaotengshuai20190120.bean.MyData;
import com.example.activity.myzhaotengshuai20190120.contacts.Contacts;
import com.example.activity.myzhaotengshuai20190120.presenter.Presenter;
import com.example.activity.myzhaotengshuai20190120.presenter.PresenterImpl;
import com.example.activity.myzhaotengshuai20190120.view.IView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IView{

    private RecyclerView Shou_Recy;
    private TextView Shou_Text;
    private PresenterImpl presenter;
    private ArrayList<MyData.DataBean>mList=new ArrayList<>();
    private int index=1;
    private String name="笔记本";
    private MyShouAdapter myShouAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new PresenterImpl(this);
        HashMap<String,String>map=new HashMap<>();
        map.put("keywords",name);
        map.put("page",index+"");
        myShouAdapter = new MyShouAdapter(mList,this);
        Shou_Recy.setAdapter(myShouAdapter);
        Shou_Recy.setLayoutManager(new LinearLayoutManager(this));
        presenter.getRequsePost(Contacts.GOODS_URL,map,MyData.class);

        onClick();
    }

    private void onClick() {
        Shou_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),DiTuActivity.class);
                startActivity(intent);


            }
        });

    }


    private void initView() {
        Shou_Recy = (RecyclerView) findViewById(R.id.Shou_Recy);
        Shou_Text = (TextView) findViewById(R.id.Shou_Text);


    }

    @Override
    public void success(Object data) {

        MyData myData= (MyData) data;

        mList.addAll(myData.getData());
        myShouAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }
}
