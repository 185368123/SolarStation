package com.shuorigf.solarstaition.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.util.FragmentUtils;

/**
 * Created by clx on 2017/9/26.
 */

public abstract class ToolbarContainerActivity extends BaseActivity {

    protected TextView toolbarTitle;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_toolbar_container;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getToolbarTitle());
        FragmentUtils.replaceFragmentToActivity(R.id.container,
                getSupportFragmentManager(), newFragmentInstance());
    }

    @Override
    public void initData() {
        super.initData();
    }


    /**
     * get toolbar title
     *
     * @return string resource id
     */
    @NonNull
    protected abstract String getToolbarTitle();

    /**
     * new fragment instance
     *
     * @return fragment instance
     */
    @NonNull
    public abstract Fragment newFragmentInstance();

}
