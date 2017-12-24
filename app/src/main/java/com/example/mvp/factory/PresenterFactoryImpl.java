package com.example.mvp.factory;


import com.example.mvp.base_mvp.BaseRequestPresenter;
import com.example.mvp.base_mvp.BaseRequestView;

import java.util.ArrayList;
import java.util.List;

public class PresenterFactoryImpl<V extends BaseRequestView,
        P extends BaseRequestPresenter<V>> implements PresentFactory<V, P> {

    private final Class<P> mPresentClass;

    private PresenterFactoryImpl(Class<P> presentClass) {
        this.mPresentClass = presentClass;
    }

    public static <V extends BaseRequestView, P extends BaseRequestPresenter<V>>
        PresenterFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterFactoryImpl<>(aClass);
    }

    @Override
    public P createPresenter() {
        try {
            return mPresentClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Presenter创建失败，检查注解声明");
        }
    }
}
