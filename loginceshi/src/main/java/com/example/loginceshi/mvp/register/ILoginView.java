package com.example.loginceshi.mvp.register;

import com.example.loginceshi.base.IBaseView;

public interface ILoginView<L> extends IBaseView {
    void onSuccess(L l);
}
