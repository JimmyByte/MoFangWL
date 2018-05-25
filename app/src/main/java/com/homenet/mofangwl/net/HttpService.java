package com.homenet.mofangwl.net;

import com.homenet.mofangwl.model.ArticleListResult;
import com.homenet.mofangwl.model.ArticleResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by weijunpeng on 2018/5/22.
 */

public interface HttpService {
    @GET("getArticleList")
    abstract Observable<ArticleResult<ArticleListResult>> getArticleList1(
            @Query("pageSize") int pageSize,
            @Query("page") int page);


    @FormUrlEncoded
    @POST("getArticleList")
    Observable<ArticleResult<ArticleListResult>> getArticleList2(
            @Field("pageSize") int pageSize,
            @Field("page") int page);
}