package com.example.loginlianxi.mvp.login;

import com.example.loginlianxi.base.BasePresenter;
import com.example.loginlianxi.base.IBaseCallBack;
import com.example.loginlianxi.base.IBaseView;
import com.example.loginlianxi.bean.LoginBean;

public class ILoginPresenter extends BasePresenter<ILoginView> implements IBaseCallBack {

    private final ILoginModel model;

    public ILoginPresenter() {
        model = new ILoginModel();
    }

    public void getLogin(String username, String password) {
        model.getLogin(username, password, this);
    }

    @Override
    public void onSuccess(Object o) {
        view.onSuccess((LoginBean) o);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
