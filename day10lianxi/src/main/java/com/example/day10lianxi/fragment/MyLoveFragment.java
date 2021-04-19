package com.example.day10lianxi.fragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day10lianxi.R;
import com.example.day10lianxi.adapter.GirlAdapter;
import com.example.day10lianxi.util.DBUtil;
import com.example.day10lianxi.util.DataDbBean;
import com.example.mymvplibrary.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MyLoveFragment extends BaseFragment {

    @BindView(R.id.rv_love)
    RecyclerView rvLove;
    private ArrayList<DataDbBean> list;
    private GirlAdapter adapter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            list.clear();
            initData();
        }
    }

    @Override
    protected void initData() {
        List<DataDbBean> dataDbBeans = DBUtil.getDbUtil().queryAll();
        if (list!=null){
            list.clear();
        }
        list.addAll(dataDbBeans);
        adapter.resetList(list);
    }

    @Override
    protected void initView() {
        rvLove.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLove.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new GirlAdapter(getContext());
        rvLove.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_love;
    }
}