package com.example.activity.myzhaotengshuai20190120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.activity.myzhaotengshuai20190120.bean.XiangData;
import com.example.activity.myzhaotengshuai20190120.contacts.Contacts;
import com.example.activity.myzhaotengshuai20190120.presenter.PresenterImpl;
import com.example.activity.myzhaotengshuai20190120.view.IView;
import com.recker.flybanner.FlyBanner;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XiangQingActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private int id;
    private TextView Xiang_Text;
    private ImageView Xiang_Image;
    private TextView Xiang_Name;
    private TextView Xiang_Price;
    private PresenterImpl presenter;
    private FlyBanner Fly;
    private String[] mImagesUrl = {

            "https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg",
            "https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg",
            "https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg",

    };
    private Button Gou_Btn;
    private Button Gou_Fen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        initView();

        Intent intent = getIntent();
        id = intent.getIntExtra("pid", 0);
        presenter = new PresenterImpl(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("pid", id + "");
        presenter.getRequsePost(Contacts.XINAG_URL, map, XiangData.class);
        initNetBanner();
    }


    private void initNetBanner() {

        List<String> imgesUrl = new ArrayList<>();
        for (int i = 0; i < mImagesUrl.length; i++) {
            imgesUrl.add(mImagesUrl[i]);
        }
        Fly.setImagesUrl(imgesUrl);

    }


    @Override
    public void success(Object data) {

        if (data instanceof XiangData) {
            XiangData xiangData = (XiangData) data;
            XiangData.DataBean dataBean = xiangData.getData();

            Xiang_Name.setText(dataBean.getTitle());
            Xiang_Price.setText(dataBean.getPrice() + "");
            String images = dataBean.getImages();
            String replace = images.replace("https", "http");
            String[] split = replace.split("\\|");
            Glide.with(this).load(split[0]).into(Xiang_Image);

        }
    }

    @Override
    public void error(Object error) {

    }

    private void initView() {
        Xiang_Text = (TextView) findViewById(R.id.Xiang_Text);
        Xiang_Image = (ImageView) findViewById(R.id.Xiang_Image);
        Xiang_Name = (TextView) findViewById(R.id.Xiang_Name);
        Xiang_Price = (TextView) findViewById(R.id.Xiang_Price);
        Fly = (FlyBanner) findViewById(R.id.Fly);

        Gou_Btn = (Button) findViewById(R.id.Gou_Btn);
        Gou_Btn.setOnClickListener(this);
        Gou_Fen = (Button) findViewById(R.id.Gou_Fen);
        Gou_Fen.setOnClickListener(this);
    }

    //登陆监听
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(XiangQingActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(XiangQingActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(XiangQingActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }





    //分享的监听
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(XiangQingActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(XiangQingActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(XiangQingActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Gou_Btn:
                UMShareConfig shareConfig = new UMShareConfig();
                shareConfig.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(XiangQingActivity.this).setShareConfig(shareConfig);
                UMShareAPI.get(this).getPlatformInfo(XiangQingActivity.this, SHARE_MEDIA.QQ, authListener);

                 Toast.makeText(this,"我的",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Gou_Fen:

                UMImage umImage = new UMImage(this, R.drawable.umeng_socialize_qq);
                new ShareAction(XiangQingActivity.this).withText("hello").withMedia(umImage).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE)
                        //分享的监听
                        .setCallback(shareListener).open();


                break;
        }
    }
}
