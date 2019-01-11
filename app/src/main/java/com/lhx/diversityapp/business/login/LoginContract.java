package com.lhx.diversityapp.business.login;

import com.lhx.diversityapp.base.BaseView;
import com.lhx.diversityapp.base.interfaces.BasePresenter;

/**
 * Created by lihongxin on 2019/1/11
 */
public interface LoginContract {
    interface Presenter extends BasePresenter {
        void loginQQ();
    }

    interface View extends BaseView<Presenter> {
        void showLoginFailure();
        void showLoginSuccess();
    }
}
