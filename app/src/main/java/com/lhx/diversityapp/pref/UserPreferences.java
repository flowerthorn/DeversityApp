package com.lhx.diversityapp.pref;

import com.lib.mylibrary.utils.SpUtils;

/**
 * Created by lihongxin on 2019/1/11
 */
public class UserPreferences {

    public static void setQQOpenid(String openid) {
        SpUtils.put("qqOpenId", openid);
    }

    public static String getQQOpenid() {
        return SpUtils.get("qqOpenId", null);
    }

    public static void setQQAccessToken(String accessToken) {
        SpUtils.put("qqAccessToken", accessToken);
    }

    public static String getQQAccessToken() {
        return SpUtils.get("qqAccessToken", null);
    }

    public static void setLoginStatus(boolean isLogin) {
        SpUtils.put("LoginStatus", isLogin);
    }

    public static boolean getLoginStatus() {
        return SpUtils.get("LoginStatus", false);
    }
}
