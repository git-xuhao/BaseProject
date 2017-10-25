package com.jakehao.example.injector.components;

import com.jakehao.example.MyApp;
import com.jakehao.example.components.okhttp.OkHttpHelper;
import com.jakehao.example.data.api.gank.GankService;
import com.jakehao.example.data.api.meitu.MeituService;
import com.jakehao.example.injector.modules.ApiModule;
import com.jakehao.example.injector.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zwl on 16/9/5.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    OkHttpHelper getOkHttpHelper();

    GankService getGankService();

    MeituService getMeituService();

    void inject(MyApp mApplication);
}
