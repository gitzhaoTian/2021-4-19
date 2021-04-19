package com.example.day10lianxi.fragment;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day10lianxi.R;
import com.example.day10lianxi.adapter.DataAdapter;
import com.example.day10lianxi.bean.DatBean;
import com.example.day10lianxi.retrofit.IDataPresenter;
import com.example.day10lianxi.retrofit.IDataView;
import com.example.mymvplibrary.ui.BaseMvpFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseMvpFragment<IDataView, IDataPresenter> implements IDataView {

    @BindView(R.id.rv_girl)
    RecyclerView rv_girl;
    @BindView(R.id.srl_girl)
    SmartRefreshLayout srlGirl;

    private DataAdapter adapter;
    private int page = 1;
    private int count = 1;
    private List<DatBean.DataBean> data;
    private IDataPresenter presenter;

    @Override
    public void onSuccess(Object o) {
        DatBean o1 = (DatBean) o;
        data = o1.getData();
        adapter.addList(data,data);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (data!=null){
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected IDataPresenter creatPresenter() {
        return presenter;
    }

    @Override
    public void onFail(String error) {
        Log.e("TAG", "onFail: " + error);
    }

    @Override
    protected void initView() {
        rv_girl.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_girl.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new DataAdapter(getContext());
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
                adapter.addList(data,data);
                srlGirl.finishLoadMore();


            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (page>10){
                    page=1;
                }
                adapter.resetList(data,data);
                presenter.getData("page/"+page+"/count/"+count);
                srlGirl.finishRefresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
