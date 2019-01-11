package com.lhx.diversityapp.manager;

import android.app.Dialog;
import android.content.Context;

import com.lhx.diversityapp.widget.alert.OnAlertClickListener2;
import com.lhx.diversityapp.widget.alert.PmAlert;

/**
 * Created by lihongxin on 2019/1/11
 */
public class AlertDialogManager {

    public static Dialog getExitWxDialog(final Context mContext, final Callback<Dialog> onExitCallback) {

        Dialog exitDialog = PmAlert.getTwoButtonAlert(mContext,
                "退出确认",
                "您确定要退出吗？",
                "取消",
                "确认退出",
                false,
                new OnAlertClickListener2() {
                    @Override
                    public void onPositiveClick(Dialog dialog) {
                        //取消
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegativeClick(Dialog dialog) {
                        //确认退出
                        onExitCallback.onCallback(dialog);
                    }
                }
        );
        exitDialog.setCancelable(true);
        return exitDialog;
    }

    public interface Callback<T> {

        void onCallback(T obj);

    }
}
