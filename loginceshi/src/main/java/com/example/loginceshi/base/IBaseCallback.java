package com.example.loginceshi.base;

public interface IBaseCallback<T> {
    void onSuccess(T t);
    void onFail(String error);
}
