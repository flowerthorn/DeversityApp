package com.lhx.diversityapp.app;

import android.app.Application;

import com.lib.mylibrary.utils.SpUtils;

/**
 * Created by lihongxin on 2019/1/10
 */
public class MyApplication extends Application {
    public final static String TAG = "__DiversityApp.";

    @Override
    public void onCreate() {
        super.onCreate();
        SpUtils.init("diversity_sp", this);
    }
}
