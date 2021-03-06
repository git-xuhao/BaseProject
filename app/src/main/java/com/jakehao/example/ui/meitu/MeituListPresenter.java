package com.jakehao.example.ui.meitu;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jakehao.example.adapter.commonadapter.CommonAdapter;
import com.jakehao.example.adapter.commonadapter.ViewHolder;
import com.jakehao.example.bean.ImageBean;
import com.jakehao.example.data.api.meitu.MeituApi;
import com.jakehao.example.injector.PerFragment;
import com.jakehao.example.views.PLAImageView;

import com.jakehao.example.R;
import com.jakehao.example.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by zwl on 16/9/30.
 */
@PerFragment
public class MeituListPresenter extends BasePresenter<MeituListContract.View> implements MeituListContract.Presenter{

    private MeituApi mMeituApi;

    @Inject
    public MeituListPresenter(MeituApi meituApi){
        this.mMeituApi = meituApi;
    }

    @Override
    public void initAdapter(LRecyclerView recyclerView) {
        List<ImageBean> images = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        CommonAdapter commonAdapter = new CommonAdapter<ImageBean>(mActivity, R.layout.item_image, images) {
            @Override
            protected void convert(ViewHolder holder, ImageBean imagebean, int position) {
                int width = imagebean.thumbnailWidth;
                int height = imagebean.thumbnailHeight;
                PLAImageView draweView = holder.getView(R.id.image_view);
                draweView.setImageHeight(height);
                draweView.setImageWidth(width);
                holder.setImageByUrl(R.id.image_view, imagebean.imageUrl);
            }
        };
        LRecyclerViewAdapter lrecyclerAdapter = new LRecyclerViewAdapter(mActivity, commonAdapter);
        recyclerView.setAdapter(lrecyclerAdapter);
        mView.initAdapter(commonAdapter, lrecyclerAdapter);
    }

    /**
     * 获取图片
     */
    @Override
    public void getImageByKey(String title, int page, boolean isRefresh) {
        Subscription subscription = mMeituApi.getImages(title, page)
                .subscribe(imageListBean ->{
                    if(isRefresh) mView.refresh(imageListBean.imgs);
                    else mView.loadMore(imageListBean.imgs);
                }, throwable -> {

                });
        mRxManager.add(subscription);
    }

}
