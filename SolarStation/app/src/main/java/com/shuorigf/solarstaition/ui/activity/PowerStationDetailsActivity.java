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
import com.shuorigf.solarstaition.ui.fragment.powerstationdetails.BasicSurveyFragment;
import com.shuorigf.solarstaition.ui.fragment.powerstationdetails.PowerStationInformationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by clx on 2017/10/11.
 */
public class PowerStationDetailsActivity extends BaseActivity {

    @BindView(R.id.power_station_details_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.power_station_details_viewpager)
    ViewPager mViewPager;
    @BindArray(R.array.power_station_details_title)
    TypedArray mTabTitles;
    @BindView(R.id.tv_power_station_details_title)
    TextView mTitleTv;


    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_power_station_details;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mTitleTv.setText(getIntent().getStringExtra(Constants.STATION_NAME));
        initVP();
    }


    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BasicSurveyFragment.newInstance());
        fragments.add(PowerStationInformationFragment.newInstance());
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
