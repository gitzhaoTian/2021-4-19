package com.example.loginlianxi.base;

public interface IBaseCallBack<T> {
    void onSuccess(T t);
    void onFail(String error);
}
