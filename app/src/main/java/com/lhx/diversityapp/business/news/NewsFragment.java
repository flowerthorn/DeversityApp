package com.lhx.diversityapp.business.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lhx.diversityapp.R;
import com.lhx.diversityapp.base.BaseTabFragment;
import com.lhx.diversityapp.business.news.recommend.NewsRecommendFragment;
import com.lhx.diversityapp.business.news.text.NewsTextFragment;
import com.lhx.diversityapp.business.news.video.NewsVideoFragment;
import com.lhx.diversityapp.business.news.voice.NewsVoiceFragment;
import com.lib.mylibrary.utils.PhoneUtils;
import com.lib.mylibrary.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/11
 */
public class NewsFragment extends BaseTabFragment implements NewsContract.View {
    @BindView(R.id.rb_recommend)
    RadioButton rbRecommend;
    @BindView(R.id.rb_text)
    RadioButton rbText;
    @BindView(R.id.rb_voice)
    RadioButton rbVoice;
    @BindView(R.id.rb_video)
    RadioButton rbVideo;
    @BindView(R.id.rg_news)
    RadioGroup rgNews;
    @BindView(R.id.iv_line)
    ImageView ivLine;
    @BindView(R.id.view_pager_news)
    ViewPager mViewPager;


    private NewsContract.Presenter mPresenter;
    private FragmentPagerAdapter mPagerAdapter;

    private NewsRecommendFragment newsRecommendFragment;
    private NewsTextFragment newsTextFragment;
    private NewsVideoFragment newsVideoFragment;
    private NewsVoiceFragment newsVoiceFragment;

    private int lineWidth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        new NewsPresenter(this);
        initViews();
        return view;
    }

    private void initViews() {

        lineWidth = ViewUtils.dip2px(mContext, 20);
        //粗体和字体大小
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    buttonView.getPaint().setFakeBoldText(true);
                    buttonView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                } else {
                    buttonView.getPaint().setFakeBoldText(false);
                    buttonView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
            }
        };
        rbRecommend.setOnCheckedChangeListener(listener);
        rbText.setOnCheckedChangeListener(listener);
        rbVideo.setOnCheckedChangeListener(listener);
        rbVoice.setOnCheckedChangeListener(listener);

        rbRecommend.setChecked(true);


        newsRecommendFragment = new NewsRecommendFragment();
        newsTextFragment = new NewsTextFragment();
        newsVoiceFragment = new NewsVoiceFragment();
        newsVideoFragment = new NewsVideoFragment();

        mPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return newsRecommendFragment;
                    case 1:
                        return newsTextFragment;
                    case 2:
                        return newsVoiceFragment;
                    case 3:
                        return newsVideoFragment;
                    default:
                        return newsRecommendFragment;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

        mViewPager.setAdapter(mPagerAdapter);
        //radioGroup和viewPager相关联
        //radioGroup选中监听
        rgNews.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_recommend) {
                    mViewPager.setCurrentItem(0, true);
                } else if (checkedId == R.id.rb_text) {
                    mViewPager.setCurrentItem(1, true);
                } else if (checkedId == R.id.rb_voice) {
                    mViewPager.setCurrentItem(2, true);
                } else if (checkedId == R.id.rb_video) {
                    mViewPager.setCurrentItem(3, true);
                }
            }
        });

        //viewPager切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int i1) {
                setScrollLinePosition(position, positionOffset);

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position, false);
                if (position == 0) {
                    rgNews.check(R.id.rb_recommend);
                } else if (position == 1) {
                    rgNews.check(R.id.rb_text);
                } else if (position == 2) {
                    rgNews.check(R.id.rb_voice);
                } else if (position == 3) {
                    rgNews.check(R.id.rb_video);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //为下划线设置宽度
        ivLine.getLayoutParams().width = lineWidth;
        //初始化下划线的位置
        setScrollLinePosition(0, 0);
        mViewPager.setCurrentItem(0);

    }

    private void setScrollLinePosition(int position, float offsetPercent) {
        int hostPosition = getRbPosition(position);//第一个rb的位置
        int nextPosition = getRbPosition(position + 1);//第二个rb的位置
        int margin = (int) ((nextPosition - hostPosition) * offsetPercent + hostPosition);
        ((ViewGroup.MarginLayoutParams) ivLine.getLayoutParams()).setMargins(margin, 0, 0, 0);
        ivLine.requestLayout();
    }

    //重点是计算下划线滑动的初始位置
    private int getRbPosition(int position) {
        int screenWidth = PhoneUtils.getScreenWidth(mContext);
        int childCount = rgNews.getChildCount();
        int childWidth = screenWidth / childCount;//4等分
        float margin = (childWidth - lineWidth) / 2;
        return (int) (childWidth * position + margin);
    }


    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
