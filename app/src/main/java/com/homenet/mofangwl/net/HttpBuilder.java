package com.homenet.mofangwl.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by weijunpeng on 2018/5/22.
 */

public class HttpBuilder{

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 30;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static OkHttpClient.Builder addInterceptor() {
            boolean isReleased = GlobalConfig.getConfiguration(ConfigKeys.IS_RELEASED);
            if (!isReleased) {
                HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(
                        new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Timber.tag("HttpLogging").i(message);
                            }
                        }
                );
                loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                BUILDER.addInterceptor(loggerInterceptor);
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = GlobalConfig.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * Service接口
     */
    private static final class ServiceHolder {
        private static final HttpService API_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(HttpService.class);
    }

    public static HttpService getAPIService() {
        return ServiceHolder.API_SERVICE;
    }
}
