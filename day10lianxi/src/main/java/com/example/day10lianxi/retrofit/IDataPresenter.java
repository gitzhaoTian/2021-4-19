package com.example.day10lianxi.retrofit;


import com.example.day10lianxi.bean.DatBean;
import com.example.mymvplibrary.base.BasePresenter;
import com.example.mymvplibrary.base.IBaseCallBack;

public class IDataPresenter extends BasePresenter<IDataView> implements IBaseCallBack<DatBean> {

    private final DataModel model;

    public IDataPresenter() {
        model = new DataModel();
    }

    public void getData(String url) {
        model.getData(this, url);
    }

    @Override
    public void onSuccess(DatBean datBean) {
        view.onSuccess(datBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
