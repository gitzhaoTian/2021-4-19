package com.example.day10lianxi.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.day10lianxi.R;
import com.example.day10lianxi.bean.DatBean;
import com.example.day10lianxi.fragment.MyLoveFragment;
import com.example.day10lianxi.util.DBUtil;
import com.example.day10lianxi.util.DataDbBean;
import com.example.mymvplibrary.base.BaseAdapter;

import java.util.List;

public class GirlAdapter extends BaseAdapter<DataDbBean> {
    public GirlAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_girl;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, DataDbBean dataDbBean) {
        ImageView iv_url = (ImageView) viewHolder.getViewById(R.id.iv_url);
        ImageView iv_love = (ImageView) viewHolder.getViewById(R.id.iv_love);
        TextView tv_title = (TextView) viewHolder.getViewById(R.id.tv_title);
        TextView tv_desc = (TextView) viewHolder.getViewById(R.id.tv_desc);

        tv_title.setText(dataDbBean.getTitle());
        tv_desc.setText(dataDbBean.getDesc());
        Glide.with(context).load(dataDbBean.getUrl()).into(iv_url);

        SharedPreferences love = context.getSharedPreferences("love", 0);
        boolean aBoolean = love.getBoolean(dataDbBean.getTitle(), false);
        if (!aBoolean){
            iv_love.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }else {
            iv_love.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        iv_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = love.edit();
                if (aBoolean){
                    iv_love.setImageResource(R.drawable.ic_baseline_favorite_24);
                    edit.putBoolean(dataDbBean.getTitle(),false);
                    edit.commit();

                    List<DataDbBean> query = DBUtil.getDbUtil().query(dataDbBean.getTitle());
                    for (int i = 0; i < query.size(); i++) {
                        DBUtil.getDbUtil().delete(query.get(i));
                    }

                    Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }else {
                    iv_love.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    edit.putBoolean(dataDbBean.getTitle(),true);
                    edit.commit();

                    DataDbBean dataDbBean1 = new DataDbBean();
                    dataDbBean1.setDesc(dataDbBean.getDesc());
                    dataDbBean1.setTitle(dataDbBean.getTitle());
                    dataDbBean1.setUrl(dataDbBean.getUrl());
                    DBUtil.getDbUtil().insert(dataDbBean);

                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });
    }
}
