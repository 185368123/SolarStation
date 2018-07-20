package com.shuorigf.solarstaition.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.CommonFragmentPagerAdapter;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.DataDetailFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.DeviceInformationFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.ParameterSettingFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.RealTimeCurveFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.RealTimeMonitoringFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by clx on 2017/10/15.
 */

public class DeviceManageActivity extends BaseActivity {

    @BindView(R.id.device_manage_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.device_manage_viewpager)
    ViewPager mViewPager;
    @BindArray(R.array.device_manage_title)
    TypedArray mTabTitles;
    @BindView(R.id.tv_device_manage_title)
    TextView mTitleTv;


    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_device_manage;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mTitleTv.setText(getIntent().getStringExtra(Constants.DEVICE_NAME));
        initVP();
    }


    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RealTimeMonitoringFragment.newInstance());
        fragments.add(RealTimeCurveFragment.newInstance());
        fragments.add(DataDetailFragment.newInstance());
        fragments.add(ParameterSettingFragment.newInstance());
        fragments.add(DeviceInformationFragment.newInstance());

        mViewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles.getString(position);
            }
        });
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
