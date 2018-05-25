package com.homenet.mofangwl.net;

import com.homenet.mofangwl.model.ArticleResult;

import io.reactivex.functions.Function;

/**
 * Created by weijunpeng on 2018/5/22.
 */

public class ResultTransform<T> implements Function<ArticleResult<T>,T> {


    private ResultTransform() {
    }

    public static ResultTransform getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ResultTransform INSTANCE = new ResultTransform();
    }

    @Override
    public T apply(ArticleResult<T> tArticleResult) throws Exception {
        if (!"OK".equals(tArticleResult.getStatus())) {
            throw new APIException(tArticleResult.getStatus(), tArticleResult.getMsg());
        }
        return tArticleResult.getData();
    }
}
