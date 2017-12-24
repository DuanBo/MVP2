package com.example.mvp.factory;

import com.example.mvp.base_mvp.BaseRequestPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseRequestPresenter> value();
}
