package com.lhx.diversityapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.LinkedHashMap;

/**
 * Created by lihongxin on 2019/1/15
 */
public class NewsFunctionView extends FrameLayout {

    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(0, 0.75F, true);
    private View loadingView;
    private View failureView;
    private View normalView;
    private FrameLayout coverLayout;

    public NewsFunctionView(@NonNull Context context) {
        this(context, null);
        linkedHashMap.put("1","2");
    }

    public NewsFunctionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

}
