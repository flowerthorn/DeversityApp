package com.lhx.diversityapp.config;


import com.lhx.diversityapp.bean.UserItem;

public class UserInfoConfig {
    private static UserItem userItem;

    public static UserItem get() {
        return userItem;
    }

    public static void set(UserItem item) {
        userItem = item;
    }
}
