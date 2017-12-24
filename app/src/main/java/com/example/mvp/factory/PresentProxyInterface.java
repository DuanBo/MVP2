package com.example.mvp.factory;


import com.example.mvp.base_mvp.BaseRequestPresenter;
import com.example.mvp.base_mvp.BaseRequestView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface PresentProxyInterface<V extends BaseRequestView,
        P extends BaseRequestPresenter<V>> {
    void setPresenterFactory(PresentFactory<V, P> presentFactory);

    PresentFactory<V, P> getPresenterFactory();

    P getPresenter();
}
