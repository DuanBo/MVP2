package com.example.mvp.mvp;


import com.alibaba.fastjson.JSONObject;
import com.example.mvp.api.LoginAPI;
import com.example.mvp.util.RetrofitUtils;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RequestModel{
    private static final String BASE_URL = "http://10.19.7.204:8080/";

    private Subscription subscription;

    void loginRequest(String userName, String userPassword,
                      Subscriber<JSONObject> subscriber) {
        subscription = (new RetrofitUtils())
                .getRetrofit(BASE_URL)
                .create(LoginAPI.class)
                .login(userName, userPassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    protected void interrupt() {
        subscription.unsubscribe();
        subscription = null;
    }
}
