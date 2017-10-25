package com.jakehao.example.ui.meitu;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jakehao.example.adapter.commonadapter.CommonAdapter;
import com.jakehao.example.bean.ImageBean;
import com.jakehao.example.ui.base.IPresenter;

import java.util.List;

/**
 * Created by zwl on 16/9/30.
 */

public interface MeituListContract {

    interface View {

        void initAdapter(CommonAdapter commonAdapter, LRecyclerViewAdapter lRecyclerViewAdapter);

        void refresh(List<ImageBean> imageList);

        void loadMore(List<ImageBean> imageList);
    }

    interface Presenter extends IPresenter<View> {

        void initAdapter(LRecyclerView recyclerView);

        void getImageByKey(String title, int page, boolean isRefresh);
    }

}
