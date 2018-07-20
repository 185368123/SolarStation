package com.shuorigf.solarstaition.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.CommonFragmentPagerAdapter;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.ui.fragment.main.AlarmFragment;
import com.shuorigf.solarstaition.ui.fragment.main.HomePageFragment;
import com.shuorigf.solarstaition.ui.fragment.main.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by clx on 2017/9/28.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.main_bottom_tabs)
    TabLayout mBottomTabs;
    @BindView(R.id.main_bottom_viewpager)
    ViewPager mViewpager;
    @BindArray(R.array.main_bottom_title)
    TypedArray mTabTitles;
    @BindArray(R.array.main_bottom_icon)
    TypedArray mTabIcons;


    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        initVP();
        initTabs();
    }

    /**
     * 初始化底部Tab
     */
    private void initTabs() {
        for (int i = 0; i < mBottomTabs.getTabCount(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.main_bottom_tab, null, false);
            TextView tab = view.findViewById(R.id.tv_main_bottom_tab);
            tab.setText(mTabTitles.getText(i));
            tab.setCompoundDrawablesWithIntrinsicBounds(null, mTabIcons.getDrawable(i), null, null);
            if (mBottomTabs.getTabAt(i) != null)
                mBottomTabs.getTabAt(i).setCustomView(view);
        }
    }

    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomePageFragment.getInstance());
        fragments.add(ProjectFragment.getInstance());
        fragments.add(AlarmFragment.getInstance());
        mViewpager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        mViewpager.setOffscreenPageLimit(fragments.size());
        mBottomTabs.setupWithViewPager(mViewpager);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
