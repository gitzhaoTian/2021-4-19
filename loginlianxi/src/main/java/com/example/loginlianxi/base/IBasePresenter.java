package com.example.loginlianxi.base;

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);
    void detachView();
}
