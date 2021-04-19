package com.example.mvp.api;

import com.example.mvp.bean.DatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    String BASEURL = "https://gank.io/api/v2/data/category/Girl/type/Girl/";
    @GET()
    Observable<DatBean> getData(@Url String url);
}
