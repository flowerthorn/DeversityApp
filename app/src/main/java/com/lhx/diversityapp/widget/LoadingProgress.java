package com.lhx.diversityapp.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhx.diversityapp.R;

import java.lang.ref.WeakReference;


/**
 * Created by lihongxin on 2018/1/1.
 */

public class LoadingProgress extends android.app.Dialog {

    private ImageView surround;
    private Context context;
    private View contentView;
    private static WeakReference<LoadingProgress> softLoadingProgress;

    private LoadingProgress(Context ctx) {
        super(ctx, R.style.LoadingProgress);
        context = ctx;
        contentView = View.inflate(context, R.layout.layout_loading_progress, null);
        surround = (ImageView) contentView.findViewById(R.id.surround);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getShowing() != null) {
                    getShowing().dismiss();
                }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        LoadingProgress showing = getShowing();
        if (showing != null) {
            showing.dismiss();
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setContentView(contentView);
        this.setCancelable(false);
    }

    @Override
    public void show() {
        super.show();
        Animation rotatedAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_rotation);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        rotatedAnimation.setInterpolator(linearInterpolator);
        surround.startAnimation(rotatedAnimation);
    }


    @Override
    public void dismiss() {
        if (!isShowing()) {
            return;
        }
        super.dismiss();
        surround.clearAnimation();
    }


    public static void showCancelable(Context context, Object content) {
        LoadingProgress loadingProgress = get(context, content);
        loadingProgress.setCancelable(true);

        loadingProgress.show();
    }


    public static void show(Context context, Object content) {
        get(context, content).show();
    }

    public static LoadingProgress get(Context context, Object content) {
        dismissCurrentIfExists();
        LoadingProgress loadingProgress = new LoadingProgress(context);
        softLoadingProgress = new WeakReference<>(loadingProgress);
        TextView tv_content = (TextView) loadingProgress.contentView.findViewById(R.id.content);
        if (content == null) {
            tv_content.setText(null);
        } else if (content instanceof String) {
            tv_content.setText(((String) content));
        } else if (content instanceof Integer) {
            tv_content.setText(((Integer) content));
        }
        return loadingProgress;
    }

    public static void dismissCurrentIfExists() {
        LoadingProgress showing = getShowing();
        if (showing != null) {
            showing.dismiss();
        }
    }


    public static LoadingProgress getShowing() {
        if (softLoadingProgress != null) {
            LoadingProgress dialog = softLoadingProgress.get();
            if (dialog != null && dialog.isShowing()) {
                return dialog;
            }
        }
        return null;
    }
}
