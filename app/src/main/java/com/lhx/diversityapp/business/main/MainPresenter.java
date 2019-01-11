package com.lhx.diversityapp.business.main;

import com.lhx.diversityapp.business.login.LoginActivity;

/**
 * Created by lihongxin on 2019/1/11
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void gotoQQLogin() {
        LoginActivity.startSelf(mView.getContext());
    }
}
