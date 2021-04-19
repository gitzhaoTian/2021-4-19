package com.example.loginlianxi.mvp.login;

import com.example.loginlianxi.api.ApiService;
import com.example.loginlianxi.base.IBaseCallBack;
import com.example.loginlianxi.base.IBaseModel;
import com.example.loginlianxi.bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ILoginModel implements IBaseModel {
    public void getLogin(String username, String password, IBaseCallBack callBack){
        new Retrofit.Builder()
                .baseUrl(ApiService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        callBack.onSuccess(loginBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onFail("网络异常:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
