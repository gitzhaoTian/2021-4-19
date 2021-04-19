package com.example.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp.R;
import com.example.mvp.base.BaseAdapter;
import com.example.mvp.bean.DatBean;

public class DataAdapter extends BaseAdapter<DatBean.DataBean> {
    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_girl;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, DatBean.DataBean dataBean) {
        ImageView iv_url = (ImageView) viewHolder.getViewById(R.id.iv_url);
        TextView tv_title = (TextView) viewHolder.getViewById(R.id.tv_title);
        TextView tv_desc = (TextView) viewHolder.getViewById(R.id.tv_desc);

        tv_title.setText(dataBean.getTitle());
        tv_desc.setText(dataBean.getDesc());
        Glide.with(context).load(dataBean.getUrl()).into(iv_url);
    }
}
