package com.jakehao.example.ui.gank;

import com.jakehao.example.adapter.commonadapter.CommonAdapter;
import com.jakehao.example.bean.GankBean;
import com.jakehao.example.loadmore.RecyclerViewFinal;
import com.jakehao.example.ui.base.IPresenter;

import java.util.List;

/**
 * Created by zwl on 16/9/6.
 */
public interface GankContract {

    interface View {
        void initAdapter(CommonAdapter commonAdapter);

        void refresh(List<GankBean.Gank> ganks);

        void showError();

        void loadMore(List<GankBean.Gank> ganks);
    }

    interface Presenter extends IPresenter<View> {

        void initAdapter(RecyclerViewFinal recyclerViewFinal);

        void getGankData(String title, int page, boolean isRefresh);
    }
}
