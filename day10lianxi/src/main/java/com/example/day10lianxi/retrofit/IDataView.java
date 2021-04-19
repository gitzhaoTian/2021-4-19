package com.example.day10lianxi.retrofit;


import com.example.mymvplibrary.base.IBaseView;

public interface IDataView<T> extends IBaseView {
    void onSuccess(T t);
}
