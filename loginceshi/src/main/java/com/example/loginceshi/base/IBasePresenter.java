package com.example.loginceshi.base;

public interface IBasePresenter<V> {
    void attachView(V view);
    void detachView();
}
