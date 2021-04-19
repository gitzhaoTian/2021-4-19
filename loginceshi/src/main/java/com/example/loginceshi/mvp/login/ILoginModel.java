package com.example.loginceshi.mvp.login;

import com.example.loginceshi.api.ApiService;
import com.example.loginceshi.base.IBaseCallback;
import com.example.loginceshi.base.IBaseModel;
import com.example.loginceshi.bean.Bean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ILoginModel implements IBaseModel {
    public void getLogin(String username, String password, IBaseCallback<Bean> callback){
        new Retrofit.Builder()
                .baseUrl(ApiService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Bean bean) {
                        callback.onSuccess(bean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onFail("网络异常"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
