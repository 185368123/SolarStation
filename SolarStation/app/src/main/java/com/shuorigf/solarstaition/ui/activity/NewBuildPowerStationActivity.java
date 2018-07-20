package com.shuorigf.solarstaition.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.ui.fragment.NewBuildPowerStationFragment;

/**
 * Created by clx on 2017/10/15.
 */

public class NewBuildPowerStationActivity extends ToolbarContainerActivity {

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.new_build_power_station);
    }

    @NonNull
    @Override
    public Fragment newFragmentInstance() {
        return NewBuildPowerStationFragment.newInstance();
    }
}
