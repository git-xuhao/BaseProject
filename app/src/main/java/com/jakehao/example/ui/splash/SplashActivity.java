package com.jakehao.example.ui.splash;

import android.view.View;

import com.jakehao.example.ui.main.MainActivity;
import com.jakehao.example.R;
import com.jakehao.example.ui.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 过渡页
 * Created by zwl on 16/9/5.
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View{
    @Override
    public void initInjector() {
        mActivityComponent.inject(SplashActivity.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initEventAndData() {
        SetTranslanteBar();
        // android隐藏底部虚拟键NavigationBar实现全屏
        ButterKnife.findById(this, R.id.splash_layout).setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mPresenter.checkIsFirstIn(SplashActivity.this);
    }

    @Override
    public void readyGoMain() {
        MainActivity.startActivity(SplashActivity.this);
        finish();
    }

    @Override
    public void readyGoGuide() {
        readyGoMain();
    }

    @Override
    public void onBackPressed() {
    }
}
