package com.example.loginlianxi.api;

import com.example.loginlianxi.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String BASEURL = "https://www.wanandroid.com/";
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("username") String username,@Field("password") String password);
}
