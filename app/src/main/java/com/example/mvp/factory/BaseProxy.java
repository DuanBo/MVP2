package com.example.mvp.factory;


import android.os.Bundle;

import com.example.mvp.base_mvp.BaseRequestPresenter;
import com.example.mvp.base_mvp.BaseRequestView;

public class BaseProxy <V extends BaseRequestView, P extends BaseRequestPresenter<V>> implements PresentProxyInterface<V, P>{

    private static final String PRESENT_KEY = "presenter_key";

    private PresentFactory<V, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttachedView;

    public BaseProxy(PresentFactory<V, P> presentFactory) {
        this.mFactory = presentFactory;
    }

    @Override
    public void setPresenterFactory(PresentFactory<V, P> presentFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("---");
        }
        this.mFactory = presentFactory;
    }

    @Override
    public PresentFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    @Override
    public P getPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENT_KEY));
            }
        }
        return mPresenter;
    }

    public void onResume(V mV) {
        getPresenter();
        if (mPresenter != null && !mIsAttachedView) {
            mPresenter.onAttachView(mV);
            mIsAttachedView = true;
        }
    }

    public void onDetachView() {
        if (mPresenter != null && mIsAttachedView) {
            mPresenter.onDetachView();
            mIsAttachedView = false;
        }
    }

    public void onDestory() {
        if (mPresenter != null) {
            onDetachView();
            mPresenter.onDestoryPresenter();
            mPresenter = null;
        }
    }

    public Bundle onSaveInstance() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENT_KEY, presenterBundle);
        }
        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstance) {
        mBundle = savedInstance;
    }
}
