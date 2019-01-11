package com.lhx.diversityapp.third;

import android.app.Activity;

import com.lhx.diversityapp.app.AppConstant;
import com.lhx.diversityapp.app.MyApplication;
import com.lhx.diversityapp.pref.UserPreferences;
import com.lhx.diversityapp.widget.LoadingProgress;
import com.lhx.diversityapp.widget.ToastUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import interfaces.QQLoginResultListener;

public class QQLogin {

    private static Tencent mTencent;
    protected QQLoginResultListener mListener;
    protected static QQLogin sInstance;
    private Activity mActivity;

    public QQLogin(Activity activity) {
        mActivity = activity;
    }

    public static QQLogin getQQLogin(Activity activity) {
        if (sInstance == null) {
            sInstance = new QQLogin(activity);
        }
        return sInstance;
    }

    public void start(QQLoginResultListener listener) {
        mListener = listener;
        verifyLoginInfo();
    }


    protected void verifyLoginInfo() {
        mTencent = Tencent.createInstance(AppConstant.QQ_APP_ID, MyApplication.getContext());
        LoadingProgress.show(mActivity, "正在登录");
        IUiListener listener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                // 通知QQ验证成功，发送通知
                LoadingProgress.dismissCurrentIfExists();
                String openId = mTencent.getOpenId();
                String accessToken = mTencent.getAccessToken();
                UserPreferences.setQQOpenid(openId);
                UserPreferences.setQQAccessToken(accessToken);
                mListener.onLoginSuccess(mTencent.getQQToken());
            }

            @Override
            public void onError(UiError uiError) {
                // 通知QQ验证失败，发送通知
                LoadingProgress.dismissCurrentIfExists();
                ToastUtils.showShortToast("verifyLoginInfo 登录失败");
                mListener.onLoginFailure();


            }

            @Override
            public void onCancel() {
                LoadingProgress.dismissCurrentIfExists();
                ToastUtils.showLongToast("登录取消");
                mListener.onLoginFailure();


            }

        };
        mTencent.login(mActivity, "get_simple_userinfo", listener);

    }

}
