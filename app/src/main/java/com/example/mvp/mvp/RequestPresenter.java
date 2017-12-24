package com.example.mvp.mvp;


import com.alibaba.fastjson.JSONObject;
import com.example.mvp.base_mvp.BaseRequestPresenter;

import rx.Subscriber;

public class RequestPresenter extends BaseRequestPresenter<RequestView> {
    private RequestModel mRequestModel;

    public RequestPresenter() {
        this.mRequestModel = new RequestModel();
    }

    public void clickRequest(String userName, String userPassword) {
        if (getView() != null) {
            getView().requestLoading();
            mRequestModel.loginRequest(userName, userPassword, new Subscriber<JSONObject>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    getView().resultFailure(e.getMessage());
                    getView().resultFinish();
                }

                @Override
                public void onNext(JSONObject object) {
                    getView().resultSuccess(object);
                    getView().resultFinish();
                }
            });
        }
    }

    public void interrupt() {
        mRequestModel.interrupt();
    }
}
