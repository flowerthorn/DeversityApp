package com.lhx.diversityapp.pref;

import com.lib.mylibrary.utils.SpUtils;

/**
 * Created by lihongxin on 2019/1/11
 */
public class UserPreferences {

    public static void setLoginStatus(boolean isLogin) {
        SpUtils.put("LoginStatus", isLogin);
    }

    public static void getLoginStatus() {
        SpUtils.get("LoginStatus", false);
    }
}
