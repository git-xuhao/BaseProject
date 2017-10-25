package com.jakehao.example.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jakehao.example.adapter.BasePagerAdapter;
import com.jakehao.example.ui.base.BaseFragment;
import com.jakehao.example.ui.meitu.MeituListFragment;
import com.jakehao.example.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zwl on 16/9/30.
 */

public class MeituMainFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<Fragment> mFragments = new ArrayList<>();
    private BasePagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_meitu_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initEventAndData() {
        List<String> contentList = new ArrayList<>();
        String[] contents = getResources().getStringArray(R.array.images_category_list);
        Collections.addAll(contentList, contents);
        for (int i = 0; i < contentList.size(); i++) {
            mFragments.add(MeituListFragment.newInstance(contentList.get(i)));
        }
        mPagerAdapter = new BasePagerAdapter(getChildFragmentManager(), mFragments, contentList);
        mViewpager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    protected void lazyLoadData() {
    }
}
