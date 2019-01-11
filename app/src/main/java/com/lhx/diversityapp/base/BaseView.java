package com.lhx.diversityapp.base;

import android.app.Activity;

/**
 * Created by lihongxin on 2019/1/10
 */
public interface BaseView<T> {
    Activity getContext();

    void setPresenter(T presenter);
}
