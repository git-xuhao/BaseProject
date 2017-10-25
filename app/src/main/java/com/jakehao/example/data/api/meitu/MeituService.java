package com.jakehao.example.data.api.meitu;

import com.jakehao.example.bean.ImageListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zwl on 16/10/13.
 */

public interface MeituService {

    @GET("/data/imgs")
    rx.Observable<ImageListBean> getImageList(@Query("col") String col, @Query("tag") String tag, @Query("pn") int pn, @Query("rn") int rn, @Query("from") int from);
}
