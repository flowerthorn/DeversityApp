package com.lhx.diversityapp.business.news.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhx.diversityapp.R;
import com.lhx.diversityapp.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/14
 */
public class NewsListBaseFragment extends BaseFragment implements NewsListBaseContract.View {

    @BindView(R.id.recycler_view_news)
    RecyclerView recyclerViewNews;
    @BindView(R.id.swipe_refresh_layout)
    SmartRefreshLayout swipeRefreshLayout;
    private NewsListBaseContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_base, container, false);
        ButterKnife.bind(this, view);
        new NewsListBasePresenter(this);
        return view;
    }

    @Override
    public void setPresenter(NewsListBaseContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void showNewsNormalView() {

    }

    @Override
    public void showNewsFailureView() {

    }

    @Override
    public void setEnableRefresh(boolean enabled) {

    }

    @Override
    public void setEnableLoadMore(boolean enabled) {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void finishLoadMore() {

    }

}
