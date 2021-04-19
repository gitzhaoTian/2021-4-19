package com.example.mvp.retrofit;


import com.example.mvp.base.IBaseView;

public interface IDataView<T> extends IBaseView {
    void onSuccess(T t);
}
