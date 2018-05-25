package com.homenet.mofangwl.view.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.homenet.mofangwl.R;
import com.homenet.mofangwl.view.widget.BaseLayout;
import com.homenet.mofangwl.view.widget.LayoutSetting;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Method;

/**
 * Created by weijunpeng on 2018/5/24.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * title资源id
     */
    private int mLayoutTitleId;
    /**
     * 是否设置主题色沉浸头
     */
    private boolean isThemeBar;

    protected LayoutInflater mLayoutInflater;
    private BaseLayout mBaseLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutInflater = LayoutInflater.from(this);
        try {
            Class<?>[] parameterTypes = {Bundle.class};
            Method method = getClass().getDeclaredMethod("onCreate", parameterTypes);
            LayoutSetting setting = method.getAnnotation(LayoutSetting.class);
            if (setting == null) {
                isThemeBar = true;
                mLayoutTitleId = R.layout.title_bar;
            } else {
                isThemeBar = setting.isThemeBar();
                mLayoutTitleId = setting.titleId();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            isThemeBar = true;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        mBaseLayout = new BaseLayout(this, mLayoutTitleId, layoutResID, mLayoutInflater);
        super.setContentView(mBaseLayout);
        initSystemBar();
    }

    @Override
    public void setContentView(View view) {
        mBaseLayout = new BaseLayout(this, mLayoutTitleId, view, mLayoutInflater);

        super.setContentView(mBaseLayout);
        initSystemBar();
    }

    /**
     * 初始化沉浸头
     */
    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            setTranslucentStatus(true);
            if (isThemeBar) {
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintColor(getResources().getColor(R.color.primary));
                SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
                mBaseLayout.setPadding(0,
                        config.getPixelInsetTop(false),
                        config.getPixelInsetRight(),
                        config.getPixelInsetBottom());
            }
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
