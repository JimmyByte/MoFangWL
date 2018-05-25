package com.homenet.mofangwl.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homenet.mofangwl.R;
import com.homenet.mofangwl.view.fragment.HomeFragment;
import com.homenet.mofangwl.view.fragment.InterviewFragment;
import com.homenet.mofangwl.view.fragment.MessageFragment;
import com.homenet.mofangwl.view.fragment.UserFragment;
import com.homenet.mofangwl.view.widget.LayoutSetting;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.tv_main_home)
    TextView tvMainHome;
    @BindView(R.id.tv_main_interview)
    TextView tvMainInterview;
    @BindView(R.id.tv_main_message)
    TextView tvMainMessage;
    @BindView(R.id.tv_main_message_red)
    View tvMainMessageRed;
    @BindView(R.id.tv_main_message_count)
    TextView tvMainMessageCount;
    @BindView(R.id.rl_main_message)
    RelativeLayout rlMainMessage;
    @BindView(R.id.tv_main_user)
    TextView tvMainUser;
    private HomeFragment mainfragment;
    private InterviewFragment resourceFragment;
    private MessageFragment orderFragment;
    private UserFragment mineFragment;

    private FragmentManager fragmentManager;


    @Override
    @LayoutSetting(isThemeBar = false, titleId = -1)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onViewClicked(tvMainHome);
    }


    /**
     * 取消选中
     */
    private void selected() {
        tvMainHome.setSelected(false);
        tvMainInterview.setSelected(false);
        rlMainMessage.setSelected(false);
        tvMainUser.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (mainfragment != null) {
            transaction.hide(mainfragment);
        }
        if (resourceFragment != null) {
            transaction.hide(resourceFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @OnClick({R.id.tv_main_home, R.id.tv_main_interview, R.id.rl_main_message, R.id.tv_main_user})
    public void onViewClicked(View view) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (view.getId()) {
            case R.id.tv_main_home:
                selected();
                tvMainHome.setSelected(true);
                if (mainfragment == null) {
                    mainfragment = new HomeFragment();
                    transaction.add(R.id.container, mainfragment);
                } else {
                    transaction.show(mainfragment);
                }
                transaction.commit();
                setAnimator(tvMainHome);
                break;

            case R.id.tv_main_interview:
                selected();
                tvMainInterview.setSelected(true);

                if (resourceFragment == null) {
                    resourceFragment = new InterviewFragment();
                    transaction.add(R.id.container, resourceFragment);
                } else {
                    transaction.show(resourceFragment);
                }
                transaction.commit();
                setAnimator(tvMainInterview);
                break;

            case R.id.rl_main_message:
                selected();
                rlMainMessage.setSelected(true);

                if (orderFragment == null) {
                    orderFragment = new MessageFragment();
                    transaction.add(R.id.container, orderFragment);
                } else {
                    transaction.show(orderFragment);
                }
                transaction.commit();
                setAnimator(rlMainMessage);
                break;
            case R.id.tv_main_user:
                selected();
                tvMainUser.setSelected(true);

                if (mineFragment == null) {
                    mineFragment = new UserFragment();
                    transaction.add(R.id.container, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                transaction.commit();
                setAnimator(tvMainUser);
                break;

        }

    }

    /**
     * 缩放view
     * @param view
     */
    private void setAnimator(View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1, 1.3f, 1),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 1.3f, 1)
        );
        set.setDuration(500).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }
}
