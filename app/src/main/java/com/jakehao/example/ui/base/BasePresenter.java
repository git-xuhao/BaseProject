package com.jakehao.example.ui.base;

import android.content.Context;

import com.jakehao.example.rxjava.RxManager;

/**
 * Created by zwl on 16/9/30.
 */

public abstract class BasePresenter<T> implements IPresenter<T> {
    public Context mActivity;
    protected T mView;
    protected RxManager mRxManager = new RxManager();

    @Override
    public void attachView(T view, Context context) {
        this.mView = view;
        this.mActivity = context;
        this.onStart();
    }

    @Override
    public void detachView() {
        this.mView = null;
        mRxManager.clear();
    }

    public void onStart(){}

}