package com.example.loginceshi.mvp.register;

import com.example.loginceshi.base.BasePresenter;
import com.example.loginceshi.base.IBaseCallback;
import com.example.loginceshi.bean.Bean;

public class ILoginPresenter extends BasePresenter<ILoginView<Bean>> implements IBaseCallback<Bean> {

    private final ILoginModel model;

    public ILoginPresenter() {
        model = new ILoginModel();
    }

    public void getLogin(String username, String password,String repassword) {
        model.getLogin(username, password,repassword, this);
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
