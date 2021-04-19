package com.example.mymvplibrary.ui;

import com.example.mymvplibrary.base.BasePresenter;
import com.example.mymvplibrary.base.IBaseView;

public abstract class BaseMvpFragment<V extends IBaseView,P extends BasePresenter> extends BaseFragment implements IBaseView {
    protected P presenter;

    @Override
    protected void initData() {
        presenter = creatPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }

    protected abstract P creatPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
