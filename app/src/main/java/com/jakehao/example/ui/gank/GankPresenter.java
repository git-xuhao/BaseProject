package com.jakehao.example.ui.gank;

import android.support.v7.widget.LinearLayoutManager;

import com.jakehao.example.adapter.commonadapter.CommonAdapter;
import com.jakehao.example.adapter.commonadapter.ViewHolder;
import com.jakehao.example.bean.GankBean;
import com.jakehao.example.injector.PerFragment;
import com.jakehao.example.R;
import com.jakehao.example.data.api.gank.GankApi;
import com.jakehao.example.loadmore.RecyclerViewFinal;
import com.jakehao.example.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by zwl on 16/9/6.
 */
@PerFragment
public class GankPresenter extends BasePresenter<GankContract.View> implements GankContract.Presenter{

    private GankApi mGankApi;

    @Inject
    public GankPresenter(GankApi gankApi) {
        this.mGankApi = gankApi;
    }

    //初始化adapter,CommonAdapter代码量有点多,建议放Presenter层
    @Override
    public void initAdapter(RecyclerViewFinal recyclerViewFinal) {
        List<GankBean.Gank> ganks = new ArrayList<>();
        recyclerViewFinal.setLoadMoreView(mActivity);
        recyclerViewFinal.setLayoutManager(new LinearLayoutManager(mActivity));
        CommonAdapter commonAdapter = new CommonAdapter<GankBean.Gank>(mActivity, R.layout.item_gank, ganks) {
            @Override
            protected void convert(ViewHolder holder, GankBean.Gank gank, int position) {
                holder.setText(R.id.item_desc, gank.desc);
                holder.setSimpleDrawByUrl(R.id.item_avator, gank.url);
            }
        };
        recyclerViewFinal.setAdapter(commonAdapter);
        recyclerViewFinal.setHasLoadMore(true);
        mView.initAdapter(commonAdapter);
    }

    /**
     * 获取gank数据
     */
    @Override
    public void getGankData(String title, int page, boolean isRefresh) {
        Subscription subscription = mGankApi.getGankData(title, page)
                .subscribe(ganks -> {
                    if(isRefresh) mView.refresh(ganks);
                    else mView.loadMore(ganks);
                }, throwable -> {
                    mView.showError();
                });
        mRxManager.add(subscription);
    }
}
