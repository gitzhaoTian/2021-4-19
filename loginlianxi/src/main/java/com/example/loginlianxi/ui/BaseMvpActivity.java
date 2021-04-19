package com.example.loginlianxi.ui;

import com.example.loginlianxi.base.BasePresenter;
import com.example.loginlianxi.base.IBaseView;

public abstract class BaseMvpActivity<P extends BasePresenter,V extends IBaseView> extends BaseActivity implements IBaseView {
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
