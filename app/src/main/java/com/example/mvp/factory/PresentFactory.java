package com.example.mvp.factory;


import com.example.mvp.base_mvp.BaseRequestPresenter;
import com.example.mvp.base_mvp.BaseRequestView;

public interface PresentFactory<V extends BaseRequestView, P extends BaseRequestPresenter<V>> {
    P createPresenter();
}
