package com.jakehao.example.ui.gank;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.jakehao.example.R;
import com.jakehao.example.adapter.ContentPagerAdapter;
import com.jakehao.example.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * TabLayout+ViewPager页面,Gank主页
 * Created by zwl on 16/9/6.
 */
public class ContentFragment extends BaseFragment {
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    private ContentPagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initEventAndData() {
        List<String> contentList = new ArrayList<>();
        String[] contents = getResources().getStringArray(R.array.gank_content);
        Collections.addAll(contentList, contents);
        mPagerAdapter = new ContentPagerAdapter(getChildFragmentManager(), contentList);
        mViewpager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    protected void lazyLoadData() {

    }
}
