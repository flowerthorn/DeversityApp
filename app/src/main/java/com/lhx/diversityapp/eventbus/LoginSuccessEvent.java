package com.lhx.diversityapp.eventbus;

import com.lhx.diversityapp.bean.UserItem;

public class LoginSuccessEvent {


    private UserItem userInfo;

    public LoginSuccessEvent(UserItem userInfo) {
        this.userInfo = userInfo;
    }

    public UserItem getUserInfo() {
        return userInfo;
    }
}
