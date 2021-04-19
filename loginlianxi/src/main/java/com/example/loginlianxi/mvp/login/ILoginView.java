package com.example.loginlianxi.mvp.login;

import com.example.loginlianxi.base.IBaseView;

public interface ILoginView<L> extends IBaseView {
    void onSuccess(L l);
}
