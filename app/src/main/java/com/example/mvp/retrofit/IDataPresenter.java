package com.example.mvp.retrofit;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.base.IBaseCallBack;
import com.example.mvp.bean.DatBean;

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
