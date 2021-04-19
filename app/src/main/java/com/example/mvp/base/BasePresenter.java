package com.example.mvp.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V view;
    WeakReference<V> weakReference;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        this.view = weakReference.get();
    }

    @Override
    public void detachView() {
        if (view!=null){
            view = null;
        }
    }
}
