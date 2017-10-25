package com.jakehao.example.data.api.gank;

import com.jakehao.example.bean.GankBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zwl on 16/10/14.
 */

public interface GankService {
    @GET("{type}/20/{page}")
    Observable<GankBean> getGankData(@Path("type") String type, @Path("page") int page);
}
