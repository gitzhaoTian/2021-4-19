package com.example.mymvplibrary.http;

import retrofit2.Retrofit;

public class HttpManager {
    private static volatile  HttpManager instance;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (instance==null){
            synchronized (HttpManager.class){
                if (instance==null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }
    public Retrofit getRetrofit(){
        new Retrofit.Builder()
                .baseUrl("");
        return null;
    }
}
