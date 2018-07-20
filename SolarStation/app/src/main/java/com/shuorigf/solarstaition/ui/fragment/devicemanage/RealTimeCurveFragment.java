package com.shuorigf.solarstaition.ui.fragment.devicemanage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bigkoo.pickerview.TimePickerView;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.CommonFragmentPagerAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.util.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by clx on 2017/10/12.
 */

public class RealTimeCurveFragment extends BaseFragment {

    @BindView(R.id.real_time_curve_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.real_time_curve_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.rb_real_time_1)
    RadioButton rbRealTime1;
    @BindView(R.id.rb_real_time_2)
    RadioButton rbRealTime2;
    @BindView(R.id.rb_real_time_3)
    RadioButton rbRealTime3;
    @BindView(R.id.rg_real_time)
    RadioGroup rgRealTime;
    @BindView(R.id.tv_date_real_time)
    Button tvDateRealTime;

    boolean yearShow = false;
    boolean monthShow = false;


    public static RealTimeCurveFragment newInstance() {
        Bundle args = new Bundle();
        RealTimeCurveFragment fragment = new RealTimeCurveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_real_time_curve;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        rbRealTime1.setChecked(true);
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        initVP();
    }


    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragments.add(RealTimeCurveChartFragment.newInstance(i));
        }

        mViewpager.setAdapter(new CommonFragmentPagerAdapter(getChildFragmentManager(), fragments));
        mViewpager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_change_tab, null, false);
            if (mTabLayout.getTabAt(i) != null)
                mTabLayout.getTabAt(i).setCustomView(view);
        }
    }


    /**
     * init event
     */
    @Override
    protected void initEvent() {
        rgRealTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_real_time_1:
                        setYearMonthShow(false, false);
                        tvDateRealTime.setVisibility(View.GONE);
                        SingleBeans.getInstance().setDate_type("0");
                        break;
                    case R.id.rb_real_time_2:
                        setYearMonthShow(true, false);
                        tvDateRealTime.setVisibility(View.VISIBLE);
                        SingleBeans.getInstance().setDate_type("1");
                        break;
                    case R.id.rb_real_time_3:
                        setYearMonthShow(true, true);
                        tvDateRealTime.setVisibility(View.VISIBLE);
                        SingleBeans.getInstance().setDate_type("2");
                        break;
                }
            }
        });
    }

    private void setYearMonthShow(boolean yearShow, boolean monthShow) {
        this.yearShow = yearShow;
        this.monthShow = monthShow;
    }

    @OnClick(R.id.tv_date_real_time)
    public void onViewClicked() {
        showTimePv();
    }

    private void showTimePv() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 11, 31);


        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                Log.e("111", "onTimeSelect: " + TimeUtils.date2String(date));
                if (yearShow && monthShow) {
                    SingleBeans.getInstance().setDate(TimeUtils.date2StringYM(date));
                    tvDateRealTime.setText(TimeUtils.date2StringYM(date));
                } else if (yearShow) {
                    SingleBeans.getInstance().setDate(TimeUtils.date2StringY(date));
                    tvDateRealTime.setText(TimeUtils.date2StringY(date));
                } else {
                    SingleBeans.getInstance().setDate("");
                    tvDateRealTime.setText(R.string.all);
                }
            }
        })
                .setType(new boolean[]{yearShow, monthShow, false, false, false, false})// 默认全部显示
                .setSubmitColor(ContextCompat.getColor(getContext(), R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(getContext(), R.color.textBlue))//取消按钮文字颜色
                .isCyclic(false)//循环与否
                .isCenterLabel(true)
                .setDate(Calendar.getInstance())// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
        pvTime.show();
    }

}
