package com.homenet.mofangwl;

import android.app.Application;

import com.homenet.mofangwl.net.GlobalConfig;

import timber.log.Timber;

/**
 * Created by weijunpeng on 2018/5/23.
 */

public class MFWLApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        GlobalConfig.init(this)
                .withApiHost("http://123.57.31.11/androidnet/")
                .withIsReleased(false)
                .configure();

        //log过滤打印树
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
