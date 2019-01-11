package com.lhx.diversityapp.app;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.lib.mylibrary.utils.SpUtils;

/**
 * Created by lihongxin on 2019/1/10
 */
public class MyApplication extends Application {
    public final static String TAG = "__DiversityApp.";
    private static MyApplication instance;
    private static Context context;

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;
        SpUtils.init("diversity_sp", this);
        AVOSCloud.initialize(this, AppConstant.LC_APP_ID, AppConstant.LC_APP_KEY);
        AVOSCloud.setDebugLogEnabled(true);
    }
}
