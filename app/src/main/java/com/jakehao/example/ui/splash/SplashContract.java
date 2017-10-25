package com.jakehao.example.ui.splash;

import android.content.Context;

import com.jakehao.example.ui.base.IPresenter;

/**
 * Created by zwl on 16/9/5.
 */
public interface SplashContract {

    interface View {

        void readyGoMain();

        void readyGoGuide();
    }

    interface Presenter extends IPresenter<View> {

        void checkIsFirstIn(Context context);
    }
}
