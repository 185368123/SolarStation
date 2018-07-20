package com.shuorigf.solarstaition.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.ui.fragment.EditingDeviceFragment;

/**
 * Created by clx on 2017/10/17.
 */

public class EditingDeviceActivity extends ToolbarContainerActivity {

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.editing_device);
    }

    @NonNull
    @Override
    public Fragment newFragmentInstance() {
        return EditingDeviceFragment.newInstance();
    }
}
