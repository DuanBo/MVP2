package com.example.mvp.mvp;


import com.alibaba.fastjson.JSONObject;
import com.example.mvp.base_mvp.BaseRequestView;

public interface RequestView extends BaseRequestView {
    void requestLoading();
    void resultSuccess(JSONObject object);
    void resultFailure(String errorMsg);
    void resultFinish();
}
