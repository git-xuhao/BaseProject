package com.jakehao.example.ui.meitu;

import android.os.Bundle;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jakehao.example.adapter.commonadapter.CommonAdapter;
import com.jakehao.example.ui.base.BaseFragment;

import com.jakehao.example.R;
import com.jakehao.example.bean.ImageBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zwl on 16/9/30.
 */

public class MeituListFragment extends BaseFragment<MeituListPresenter> implements MeituListContract.View {

    @BindView(R.id.recycler_view)
    LRecyclerView mRecyclerView;

    private LRecyclerViewAdapter mLRecyclerAdapter;
    private CommonAdapter mCommonAdapter;
    private String title;
    private int page = 1;

    public static MeituListFragment newInstance(String title) {
        MeituListFragment meituListFragment = new MeituListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", title);
        meituListFragment.setArguments(bundle);
        return meituListFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_meitu;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initEventAndData() {
        title = getArguments().getString("keyword");

        mPresenter.initAdapter(mRecyclerView);
        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getImageByKey(title, page, true);
            }

            @Override
            public void onScrollUp() {}

            @Override
            public void onScrollDown() {}

            @Override
            public void onBottom() {
                page ++;
                mPresenter.getImageByKey(title, page, false);
            }

            @Override
            public void onScrolled(int i, int i1) {}
        });
    }

    @Override
    protected void lazyLoadData() {
        mRecyclerView.setRefreshing(true);
        mPresenter.getImageByKey(title, page, true);
    }

    @Override
    public void initAdapter(CommonAdapter commonAdapter, LRecyclerViewAdapter lRecyclerViewAdapter) {
        mCommonAdapter = commonAdapter;
        mLRecyclerAdapter = lRecyclerViewAdapter;
    }

    @Override
    public void refresh(List<ImageBean> imageList) {
        mCommonAdapter.clearAddallNotify(imageList);
        mRecyclerView.refreshComplete();
        mLRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore(List<ImageBean> imageList) {
        mCommonAdapter.addAllNotify(imageList);
        mLRecyclerAdapter.notifyItemChanged(mCommonAdapter.getDataList().size() - 20, 20);
    }
}
