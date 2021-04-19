package com.example.loginceshi.mvp.login;

import com.example.loginceshi.base.BasePresenter;
import com.example.loginceshi.base.IBaseCallback;
import com.example.loginceshi.bean.Bean;

public class ILoginPresenter extends BasePresenter<ILoginView<Bean>> implements IBaseCallback<Bean> {

    private final ILoginModel model;

    public ILoginPresenter() {
        model = new ILoginModel();
    }

    public void getLogin(String username, String password) {
        model.getLogin(username, password, this);
    }

    @Override
    public void onSuccess(Bean bean) {
        view.onSuccess(bean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
