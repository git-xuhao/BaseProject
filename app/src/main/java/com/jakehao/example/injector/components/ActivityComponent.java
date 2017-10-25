package com.jakehao.example.injector.components;

import com.jakehao.example.injector.PerActivity;
import com.jakehao.example.injector.modules.ActivityModule;
import com.jakehao.example.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by zwl on 16/9/5.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

}
