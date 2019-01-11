package com.lhx.diversityapp.business.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lhx.diversityapp.R;
import com.lhx.diversityapp.base.BaseActivity;
import com.lhx.diversityapp.widget.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * o
 * Created by lihongxin on 2019/1/11
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    public static void startSelf(Activity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_open_up, 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        new LoginPresenter(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.login_qq)
    public void loginQQOnClick() {
        mPresenter.loginQQ();
    }

    @Override
    public void showLoginFailure() {
        ToastUtils.showLongToast("登录失败");
        finish();
        overridePendingTransition(0, R.anim.activity_close_down);

    }

    @Override
    public void showLoginSuccess() {
        ToastUtils.showLongToast("登录成功");
        finish();
        overridePendingTransition(0, R.anim.activity_close_down);
    }
}
