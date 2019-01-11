package com.lhx.diversityapp.interfaces;

import com.tencent.connect.auth.QQToken;

/**
 * Created by lihongxin on 2019/1/11
 */
public interface QQLoginResultListener {
    void onLoginSuccess(QQToken qqToken);

    void onLoginFailure();

}
