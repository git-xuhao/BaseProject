package com.jakehao.example.injector.components;

import android.app.Activity;

import com.jakehao.example.injector.PerFragment;
import com.jakehao.example.ui.gank.GankFragment;
import com.jakehao.example.ui.meitu.MeituListFragment;
import com.jakehao.example.injector.modules.FragmentModule;

import dagger.Component;

/**
 * Created by zwl on 16/9/5.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(GankFragment gankFragment);

    void inject(MeituListFragment meituListFragment);
}
