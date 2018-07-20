package com.shuorigf.solarstaition.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.ui.fragment.MapFragment;

/**
 * Created by clx on 2017/10/25.
 */

public class MapActivity extends ToolbarContainerActivity {

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.map);
    }

    @NonNull
    @Override
    public Fragment newFragmentInstance() {
        return MapFragment.newInstance();
    }
}
