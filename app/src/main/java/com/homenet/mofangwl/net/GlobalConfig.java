package com.homenet.mofangwl.net;

import android.content.Context;

/**
 * Created by weijunpeng on 2018/5/23.
 */

public class GlobalConfig {

    public static Configurator init(Context context) {
        Configurator configurator = Configurator.getInstance()
                .withContext(context);
        return configurator;
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
