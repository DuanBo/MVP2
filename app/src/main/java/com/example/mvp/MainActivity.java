package com.example.mvp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alibaba.fastjson.JSONObject;
import com.example.mvp.api.LoginAPI;
import com.example.mvp.base_mvp.BaseRequestActivity;
import com.example.mvp.factory.CreatePresenter;
import com.example.mvp.mvp.RequestModel;
import com.example.mvp.mvp.RequestPresenter;
import com.example.mvp.mvp.RequestView;
import com.example.mvp.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@CreatePresenter(RequestPresenter.class)
public class MainActivity extends BaseRequestActivity<RequestView,
        RequestPresenter> implements RequestView {

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.button)
    Button mButton;
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.button)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                getPresenter().clickRequest("136284008", "xbh");
                break;
        }
    }

    private ProgressDialog dialog;

    @Override
    public void requestLoading() {
        dialog = ProgressDialog.show(this, "提示", "正在登陆中", false, true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                getPresenter().interrupt();
            }
        });
    }

    @Override
    public void resultSuccess(JSONObject object) {
        mTextView.setText(object.toString());
    }

    @Override
    public void resultFailure(String errorMsg) {
        mTextView.setText(errorMsg);
    }

    @Override
    public void resultFinish() {
        dialog.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().interrupt();//不光退出活动会打断，点击dialog外部也会打断
    }
}
