package com.lhx.diversityapp.business.news;


/**
 * Created by lihongxin on 2019/1/14
 */
public class NewsPresenter implements NewsContract.Presenter {
    private NewsContract.View mView;

    public NewsPresenter(NewsContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
