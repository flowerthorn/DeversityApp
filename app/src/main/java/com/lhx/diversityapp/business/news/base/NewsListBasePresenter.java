package com.lhx.diversityapp.business.news.base;

/**
 * Created by lihongxin on 2019/1/14
 */
public class NewsListBasePresenter implements NewsListBaseContract.Presenter {
    private NewsListBaseContract.View mView;

    public NewsListBasePresenter(NewsListBaseContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void firstRequest() {

    }
}
