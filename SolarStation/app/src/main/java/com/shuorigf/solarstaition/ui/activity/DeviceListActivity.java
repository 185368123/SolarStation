package com.shuorigf.solarstaition.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.DeviceListFragment;

/**
 * Created by clx on 2018/3/20.
 */

public class DeviceListActivity extends ToolbarContainerActivity {

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.device_list);
    }

    @NonNull
    @Override
    public Fragment newFragmentInstance() {
        return DeviceListFragment.newInstance();
    }
}
