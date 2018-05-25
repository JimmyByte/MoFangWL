package com.homenet.mofangwl.net;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by weijunpeng on 2018/5/23.
 */

public class Configurator {
    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();

    private Configurator() {
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }


    public final void configure() {
        CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withContext(Context context) {
        CONFIGS.put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return this;
    }

    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withIsReleased(boolean released) {
        CONFIGS.put(ConfigKeys.IS_RELEASED, released);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) value;
    }
}
