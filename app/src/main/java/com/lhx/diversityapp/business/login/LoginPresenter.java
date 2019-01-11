package com.lhx.diversityapp.business.login;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.lhx.diversityapp.bean.UserItem;
import com.lhx.diversityapp.config.UserInfoConfig;
import com.lhx.diversityapp.eventbus.LoginSuccessEvent;
import com.lhx.diversityapp.pref.UserPreferences;
import com.lhx.diversityapp.third.QQLogin;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.List;

import interfaces.QQLoginResultListener;

/**
 * Created by lihongxin on 2019/1/11
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private UserInfo mInfo;


    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loginQQ() {
        QQLogin.getQQLogin(mView.getContext()).start(new QQLoginResultListener() {
            @Override
            public void onLoginSuccess(QQToken qqToken) {
                //第三方平台qq登录成功
                getUserInfo(qqToken);
            }

            @Override
            public void onLoginFailure() {
                mView.showLoginFailure();
            }
        });


    }

    private void getUserInfo(QQToken qqToken) {
        mInfo = new UserInfo(mView.getContext(), qqToken);
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //新增／更新开发者服务器
                updateOrSaveUser(o);
            }

            @Override
            public void onError(UiError uiError) {
                mView.showLoginFailure();
            }

            @Override
            public void onCancel() {
                mView.showLoginFailure();
            }
        });

    }

    private void updateOrSaveUser(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        try {
            final String name = jsonObject.getString("nickname");//昵称
            final String avatar = jsonObject.getString("figureurl_qq_2");  //头像图片的url
            final String gender = jsonObject.getString("gender");//性别 男/女
            //本地存储
            UserItem userItem = UserInfoConfig.get();
            if (userItem == null) {
                userItem = new UserItem();
            }
            userItem.setAvatar(avatar);
            userItem.setId(UserPreferences.getQQOpenid());
            userItem.setNickName(name);
            UserInfoConfig.set(userItem);

            //leancloud 服务器 存储
            AVQuery<AVObject> query = new AVQuery<>("QQUser");
            query.whereEqualTo("qqOpenId", UserPreferences.getQQOpenid());
            final UserItem finalUserItem = userItem;
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (list == null || list.size() == 0) {
                        //如果没有这个用户 新增用户
                        final AVObject user = new AVObject("QQUser");
                        user.put("nickname", name);
                        user.put("avatar", avatar);
                        user.put("gender", gender);
                        user.put("qqOpenId", UserPreferences.getQQOpenid());
                        user.put("qqAccessToken", UserPreferences.getQQAccessToken());
                        user.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    loginSuccess(finalUserItem);

                                } else {
                                    mView.showLoginFailure();
                                }
                            }
                        });

                    } else {
                        final AVObject qqUser = list.get(0);
                        qqUser.put("nickname", name);
                        qqUser.put("gender", gender);
                        qqUser.put("avatar", avatar);
                        qqUser.put("qqAccessToken", UserPreferences.getQQAccessToken());
                        qqUser.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    loginSuccess(finalUserItem);

                                } else {
                                    mView.showLoginFailure();
                                }

                            }
                        });

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginSuccess(UserItem userItem) {
        UserPreferences.setLoginStatus(true);
        EventBus.getDefault().post(new LoginSuccessEvent(userItem));
        mView.showLoginSuccess();


    }
}
