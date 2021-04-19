package com.example.mvp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.adapter.DataAdapter;
import com.example.mvp.bean.DatBean;
import com.example.mvp.retrofit.IDataPresenter;
import com.example.mvp.retrofit.IDataView;
import com.example.mvp.ui.BaseMvpActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity<IDataView, IDataPresenter> implements IDataView {

    @BindView(R.id.rv_girl)
    RecyclerView rv_girl;
    @BindView(R.id.srl_girl)
    SmartRefreshLayout srlGirl;
    private DataAdapter adapter;
    private IDataPresenter presenter;
    private int page = 1;
    private int count = 1;
    private List<DatBean.DataBean> data;

    @Override
    protected IDataPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onFail(String error) {
        Log.e("TAG", "onFail: " + error);
    }

    @Override
    protected void initView() {
        rv_girl.setLayoutManager(new LinearLayoutManager(this));
        rv_girl.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new DataAdapter(this);
        rv_girl.setAdapter(adapter);
        presenter = new IDataPresenter();
        presenter.getData("page/"+page+"/count/"+count);
        srlGirl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                count++;
                if (count>10){
                    count=1;
                }
                presenter.getData("page/"+page+"/count/"+count);
                adapter.addList(data);
                srlGirl.finishLoadMore();


            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (page>10){
                    page=1;
                }
                adapter.resetList(data);
                presenter.getData("page/"+page+"/count/"+count);
                srlGirl.finishRefresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object o) {
        DatBean o1 = (DatBean) o;
        data = o1.getData();
        adapter.addList(data);
    }
}