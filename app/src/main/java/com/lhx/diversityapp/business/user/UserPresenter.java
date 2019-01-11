package com.lhx.diversityapp.business.user;

import android.app.Dialog;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.lhx.diversityapp.app.AppGlobal;
import com.lhx.diversityapp.bean.UserItem;
import com.lhx.diversityapp.config.UserInfoConfig;
import com.lhx.diversityapp.eventbus.ExitAppEvent;
import com.lhx.diversityapp.manager.AlertDialogManager;
import com.lhx.diversityapp.pref.UserPreferences;
import com.lhx.diversityapp.widget.ToastUtils;
import com.lib.mylibrary.utils.PhoneUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lihongxin on 2019/1/11
 */
public class UserPresenter implements UserContract.Presenter {
    private UserContract.View mView;

    public UserPresenter(UserContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        //内存
        if (UserInfoConfig.get() != null) {
            mView.updateView(UserInfoConfig.get().getNickName(), UserInfoConfig.get().getAvatar(), UserInfoConfig.get().getGender());
        } else {
            //leanCloud
            AVQuery<AVObject> query = new AVQuery<>("QQUser");
            query.getInBackground(UserPreferences.getObjectId(), new GetCallback<AVObject>() {
                @Override
                public void done(AVObject avObject, AVException e) {
                    if (e == null) {
                        String name = (String) avObject.get("nickname");
                        String avatar = (String) avObject.get("avatar");
                        String gender = (String) avObject.get("gender");
                        UserItem userItem = UserInfoConfig.get();
                        if (userItem == null) {
                            userItem = new UserItem();
                        }
                        userItem.setAvatar(avatar);
                        userItem.setId(UserPreferences.getQQOpenid());
                        userItem.setNickName(name);
                        userItem.setGender(gender);
                        UserInfoConfig.set(userItem);
                        mView.updateView(name, avatar, gender);
                    }
                }
            });
        }

    }

    @Override
    public void gotoFeedBack() {

    }

    @Override
    public void gotoAboutUs() {

    }

    @Override
    public void exitApp() {
        AlertDialogManager.getExitWxDialog(mView.getContext(), new AlertDialogManager.Callback<Dialog>() {
            @Override
            public void onCallback(Dialog obj) {
                //退出
                obj.dismiss();
                UserPreferences.setLoginStatus(false);
                EventBus.getDefault().post(new ExitAppEvent());


            }
        }).show();
    }

    @Override
    public void gotoAppStorePraise() {
        String appPkg = AppGlobal.getPackageName();
        /**
         *  com.huawei.appmarket  华为应用市场
         *  com.bbk.appstore VIVO
         *  com.tencent.android.qqdownloader 腾讯
         *  com.oppo.market oppo
         *  com.xiaomi.market xiaomi
         *  com.qihoo.appstore 360手机助手
         *  com.baidu.appsearch 百度手机助手
         */
        //当前手机包含的应用商店
        List<String> markets = PhoneUtils.queryInstalledMarketPkgs(mView.getContext());
        if (markets.contains("com.tencent.android.qqdownloader")) {
            PhoneUtils.launchAppDetail(appPkg, "com.tencent.android.qqdownloader", mView.getContext());
            return;
        } else if (markets.contains("com.bbk.appstore")) {
            PhoneUtils.launchAppDetail(appPkg, "com.bbk.appstore", mView.getContext());
            return;
        } else if (markets.contains("com.oppo.market")) {
            PhoneUtils.launchAppDetail(appPkg, "com.oppo.market", mView.getContext());
        } else if (markets.contains("com.xiaomi.market")) {
            PhoneUtils.launchAppDetail(appPkg, "com.xiaomi.market", mView.getContext());
        } else if (markets.contains("com.huawei.appmarket")) {
            PhoneUtils.launchAppDetail(appPkg, "com.huawei.appmarket", mView.getContext());
        } else {
            ToastUtils.showShortToast("暂无发现应用商店");
        }
    }

    @Override
    public void checkUpdate() {

    }

    @Override
    public void gotoMoney() {

    }
}
