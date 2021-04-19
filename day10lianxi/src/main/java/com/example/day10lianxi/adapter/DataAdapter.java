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
import com.example.day10lianxi.util.DBUtil;
import com.example.day10lianxi.util.DataDbBean;
import com.example.mymvplibrary.base.BaseAdapter;
import com.example.mymvplibrary.base.BaseMvpAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class DataAdapter extends BaseMvpAdapter<DatBean.DataBean,DatBean.DataBean> {
    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutBan() {
        return R.layout.item_banner;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_girl;
    }

    @Override
    protected void bindBanner(BaseBanViewHolder baseBanViewHolder, List<DatBean.DataBean> ban) {
        Banner banner = (Banner) baseBanViewHolder.getViewById(R.id.banner);
        banner.setImages(ban)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        DatBean.DataBean dataBean = (DatBean.DataBean) path;
                        Glide.with(context).load(dataBean.getUrl()).into(imageView);
                    }
                }).start();
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, DatBean.DataBean dataBean) {
        ImageView iv_url = (ImageView) viewHolder.getViewById(R.id.iv_url);
        ImageView iv_love = (ImageView) viewHolder.getViewById(R.id.iv_love);
        TextView tv_title = (TextView) viewHolder.getViewById(R.id.tv_title);
        TextView tv_desc = (TextView) viewHolder.getViewById(R.id.tv_desc);

        tv_title.setText(dataBean.getTitle());
        tv_desc.setText(dataBean.getDesc());
        Glide.with(context).load(dataBean.getUrl()).into(iv_url);

        SharedPreferences love = context.getSharedPreferences("love", 0);
        boolean aBoolean = love.getBoolean(dataBean.getTitle(), false);
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
                    edit.putBoolean(dataBean.getTitle(),false);
                    edit.commit();

                    List<DataDbBean> query = DBUtil.getDbUtil().query(dataBean.getTitle());
                    for (int i = 0; i < query.size(); i++) {
                        DBUtil.getDbUtil().delete(query.get(i));
                    }

                    Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }else {
                    iv_love.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    edit.putBoolean(dataBean.getTitle(),true);
                    edit.commit();

                    DataDbBean dataDbBean = new DataDbBean();
                    dataDbBean.setDesc(dataBean.getDesc());
                    dataDbBean.setTitle(dataBean.getTitle());
                    dataDbBean.setUrl(dataBean.getUrl());
                    DBUtil.getDbUtil().insert(dataDbBean);

                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });
    }
}
