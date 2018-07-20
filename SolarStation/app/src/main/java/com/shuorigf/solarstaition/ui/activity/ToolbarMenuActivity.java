package com.shuorigf.solarstaition.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.util.FragmentUtils;

/**
 * Created by clx on 17/9/13.
 */

public abstract  class ToolbarMenuActivity extends BaseActivity {
    protected TextView toolbarTitle;
    protected TextView toolbarMenu;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_toolbar_menu;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getToolbarTitle());
        toolbarMenu = findViewById(R.id.toolbar_menu);
        toolbarMenu.setText(getToolbarMenuText());
        toolbarMenu.setTextColor(getToolbarMenuColor());
        toolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarMenuOnclick();
            }
        });
        FragmentUtils.replaceFragmentToActivity(R.id.container,
                getSupportFragmentManager(), newFragmentInstance());
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        updateTitle();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateTitle();
    }

    protected void updateTitle() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            FragmentManager.BackStackEntry entry =
                    getSupportFragmentManager().getBackStackEntryAt(count - 1);
            toolbarTitle.setText(entry.getName());
        } else if (count == 1) {
            toolbarTitle.setText(getToolbarTitle());
        }
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


    /**
     * get toolbar menu text
     *
     * @return string resource id
     */
    @NonNull
    protected abstract String getToolbarMenuText();

    /**
     * get toolbar menu color
     *
     * @return int resource id
     */
    @NonNull
    protected abstract int getToolbarMenuColor();


    /**
     * toolbar filter onclick
     *
     */
    @NonNull
    protected abstract void toolbarMenuOnclick();



}
