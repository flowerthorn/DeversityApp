package com.lhx.diversityapp.business.news.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhx.diversityapp.R;
import com.lhx.diversityapp.business.news.base.NewsListBaseFragment;

import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/14
 */
public class NewsVideoFragment extends NewsListBaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
