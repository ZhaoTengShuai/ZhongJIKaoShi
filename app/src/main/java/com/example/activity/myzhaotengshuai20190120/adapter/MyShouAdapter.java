package com.example.activity.myzhaotengshuai20190120.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.activity.myzhaotengshuai20190120.R;
import com.example.activity.myzhaotengshuai20190120.XiangQingActivity;
import com.example.activity.myzhaotengshuai20190120.bean.MyData;

import java.util.ArrayList;

public class MyShouAdapter extends RecyclerView.Adapter<MyShouAdapter.ViewHolder> {
    private ArrayList<MyData.DataBean>mList;
    private Context mContext;

    public MyShouAdapter(ArrayList<MyData.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=View.inflate(mContext,R.layout.shou_item,null);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        holder.good_name.setText(mList.get(i).getTitle());
        holder.good_price.setText(mList.get(i).getPrice()+"");
        String images = mList.get(i).getImages();
        String replace = images.replace("https", "http");
        String[] split = replace.split("\\|");
        Glide.with(mContext).load(split[0]).into(holder.good_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,XiangQingActivity.class);
                intent.putExtra("pid",mList.get(i).getPid());
                mContext.startActivity(intent);
                Toast.makeText(mContext,mList.get(i).getPid()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView good_image;
        private  TextView good_name;
        private  TextView good_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            good_image = itemView.findViewById(R.id.Good_Image);
            good_name = itemView.findViewById(R.id.Good_Name);
            good_price = itemView.findViewById(R.id.Good_Price);
        }
    }



}
