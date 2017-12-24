package com.example.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (initBundle(getIntent().getExtras())) {
            setContentView(getContentView());

            ButterKnife.bind(this);
            initData();
            initWidget();
        } else {
            finish();
        }
    }

    protected void initData() {

    }

    protected void initWidget() {

    }

    protected abstract int getContentView();

    protected boolean initBundle(Bundle bundle) {
        return true;
    }
}

