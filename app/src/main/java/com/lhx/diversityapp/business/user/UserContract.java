package com.lhx.diversityapp.business.user;

import com.lhx.diversityapp.base.BaseView;
import com.lhx.diversityapp.base.interfaces.BasePresenter;

/**
 * Created by lihongxin on 2019/1/11
 */
public interface UserContract {
    interface Presenter extends BasePresenter {
        void start();

        void gotoFeedBack();

        void gotoAboutUs();

        void exitApp();

        void gotoAppStorePraise();

        void checkUpdate();

        void gotoMoney();

    }

    interface View extends BaseView<Presenter> {
        void updateView(String nickname, String avatar, String gender);

    }
}
