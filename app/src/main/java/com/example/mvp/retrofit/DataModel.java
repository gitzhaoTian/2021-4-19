package com.example.mvp.retrofit;

import com.example.mvp.api.ApiService;
import com.example.mvp.base.IBaseCallBack;
import com.example.mvp.base.IBaseModel;
import com.example.mvp.bean.DatBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataModel implements IBaseModel {
    public void getData(IBaseCallBack callBack,String url) {
        new Retrofit.Builder()
                .baseUrl(ApiService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DatBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DatBean datBean) {
                        callBack.onSuccess(datBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onFail("网络异常:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
