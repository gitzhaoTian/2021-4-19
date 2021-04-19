package com.example.loginceshi.api;

import com.example.loginceshi.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String BASEURL = "https://www.wanandroid.com/";

    @POST("user/login")
    @FormUrlEncoded
    Observable<Bean> getLogin(@Field("username") String username,@Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Observable<Bean> getRegister(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);
}
