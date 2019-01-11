package com.lhx.diversityapp.business.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lhx.deversityapp.R;
import com.lhx.diversityapp.base.BaseActivity;
import com.lhx.diversityapp.base.BaseTabFragment;
import com.lhx.diversityapp.business.home.HomeFragment;
import com.lhx.diversityapp.business.news.NewsFragment;
import com.lhx.diversityapp.business.task.TaskFragment;
import com.lhx.diversityapp.business.user.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.rb_task)
    RadioButton rbTask;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;

    private MainContract.Presenter mPresenter;
    private HomeFragment homeFragment;
    private UserFragment userFragment;
    private NewsFragment newsFragment;
    private TaskFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainPresenter(this);
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        //可对checkId做判断 区分
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseTabFragment fragment = findFragmentById(checkedId);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, fragment, checkedId + "");
        }
        transaction.show(fragment);
        fragment.onFragmentSelected();
        if (fragment != homeFragment) transaction.hide(homeFragment);
        if (fragment != newsFragment) transaction.hide(newsFragment);
        if (fragment != userFragment) transaction.hide(userFragment);
        if (fragment != taskFragment) transaction.hide(taskFragment);
        transaction.commitAllowingStateLoss();
        rbHome.setChecked(true);
    }

    private BaseTabFragment findFragmentById(int checkedId) {
        BaseTabFragment fragment = null;
        switch (checkedId) {
            case R.id.rb_home:
                fragment = homeFragment;
                break;
            case R.id.rb_news:
                fragment = newsFragment;
                break;
            case R.id.rb_task:
                fragment = taskFragment;
                break;
            case R.id.rb_user:
                fragment = userFragment;
                break;
        }
        return fragment;
    }

    @Override
    public Activity getContext() {
        return mContext;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;

    }
}
