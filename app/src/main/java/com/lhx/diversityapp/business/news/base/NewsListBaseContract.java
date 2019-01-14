package com.lhx.diversityapp.business.news.base;

import com.lhx.diversityapp.base.BaseView;
import com.lhx.diversityapp.base.interfaces.BasePresenter;

/**
 * Created by lihongxin on 2019/1/14
 */
public interface NewsListBaseContract {
    interface Presenter extends BasePresenter {
        void loadMore();//上拉加载更多

        void refresh();//下拉刷新

        void firstRequest();//首次获取



    }

    interface View extends BaseView<Presenter> {
        void showNewsNormalView();

        void showNewsFailureView();


        void setEnableRefresh(boolean enabled);

        void setEnableLoadMore(boolean enabled);

        void finishRefresh();

        void finishLoadMore();
    }
}
