package com.example.mymvplibrary.base;

public interface IBaseCallBack<T> {
    void onSuccess(T t);
    void onFail(String error);
}
