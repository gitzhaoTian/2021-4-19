package com.example.mymvplibrary.ui;

import com.example.mymvplibrary.base.BasePresenter;
import com.example.mymvplibrary.base.IBaseView;

public abstract class BaseMvpActivity<V extends IBaseView,P extends BasePresenter> extends BaseActivity implements IBaseView {
    protected P presenter;

    @Override
    protected void initData() {
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
