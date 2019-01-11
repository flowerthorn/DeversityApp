package com.lhx.diversityapp.business.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lhx.diversityapp.R;
import com.lhx.diversityapp.base.BaseTabFragment;
import com.lhx.diversityapp.pref.UserPreferences;
import com.lhx.diversityapp.widget.CircleImageView;
import com.lhx.diversityapp.widget.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lihongxin on 2019/1/11
 */
public class UserFragment extends BaseTabFragment implements UserContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.rl_money)
    RelativeLayout llMoney;
    @BindView(R.id.tv_update_intro)
    TextView tvUpdateIntro;
    @BindView(R.id.rl_update)
    RelativeLayout llUpdate;
    @BindView(R.id.rl_about)
    RelativeLayout llAbout;
    @BindView(R.id.rl_praise)
    RelativeLayout llPraise;
    @BindView(R.id.rl_feedback)
    RelativeLayout llFeedback;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    @BindView(R.id.ll_fragment)
    LinearLayout llFragment;

    private UserContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        new UserPresenter(this);
        if (UserPreferences.getLoginStatus()) {
            llFragment.setVisibility(View.VISIBLE);
            mPresenter.start();
        }
        return view;
    }

    public void loginSuccess(String nickname, String avatar, String gender) {
        llFragment.setVisibility(View.VISIBLE);
        updateView(nickname, avatar, gender);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void updateView(String nickname, String avatar, String gender) {
        tvName.setText(nickname);
        Glide.with(this).load(avatar).into(ivAvatar);
        ivGender.setImageResource(gender.equals("男") ? R.drawable.nim_male : R.drawable.nim_female);
    }


    public void hideContainter() {
        llFragment.setVisibility(View.GONE);
    }

    @OnClick(R.id.rl_update)
    public void updateOnClick() {
        mPresenter.checkUpdate();
        ToastUtils.showLongToast("已是最新版本");
        // TODO: 2018/6/11 提供检查更新接口
    }

    @OnClick(R.id.rl_feedback)
    public void feedbackOnClick() {
        mPresenter.gotoFeedBack();
    }

    @OnClick(R.id.rl_about)
    public void aboutOnClick() {
        mPresenter.gotoAboutUs();
    }


    @OnClick(R.id.rl_praise)
    public void praiseOnClick() {
        mPresenter.gotoAppStorePraise();
    }

    @OnClick(R.id.rl_exit)
    public void exitOnClick() {
        mPresenter.exitApp();
    }

    @OnClick(R.id.rl_money)
    public void moneyOnClick() {
        mPresenter.gotoMoney();
    }

}
