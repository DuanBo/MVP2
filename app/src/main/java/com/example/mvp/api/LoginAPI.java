package com.example.mvp.api;



import com.alibaba.fastjson.JSONObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LoginAPI {
    @GET("LoginServlet")
    Observable<JSONObject> login(
            @Query("user_name") String userName,
            @Query("user_password") String userPassword);
}