package com.example.mvp.base_mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseRequestPresenter<V extends BaseRequestView> {
    private V mView;

    public void onCreatePresenter(@Nullable Bundle savedState) {

    }

    public void onAttachView(V v) {
        mView = v;
    }

    public void onDetachView() {
        mView = null;
    }

    public void onDestoryPresenter() {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public V getView() {
        return mView;
    }
}
