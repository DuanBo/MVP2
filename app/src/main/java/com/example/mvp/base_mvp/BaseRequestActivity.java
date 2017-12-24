package com.example.mvp.base_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.factory.BaseProxy;
import com.example.mvp.factory.PresentFactory;
import com.example.mvp.factory.PresentProxyInterface;
import com.example.mvp.factory.PresenterFactoryImpl;


public abstract class BaseRequestActivity<V extends BaseRequestView, P extends BaseRequestPresenter<V>>
    extends BaseActivity implements PresentProxyInterface<V, P> {

    private static final String PRESENT_SAVE_KEY ="presenter_save_key";

    private BaseProxy<V, P> mProxy = new BaseProxy<>(PresenterFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENT_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENT_SAVE_KEY, mProxy.onSaveInstance());
    }

    @Override
    public void setPresenterFactory(PresentFactory<V, P> presentFactory) {
        mProxy.setPresenterFactory(presentFactory);
    }

    @Override
    public PresentFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }
}
