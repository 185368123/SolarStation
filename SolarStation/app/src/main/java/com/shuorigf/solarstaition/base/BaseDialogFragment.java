package com.shuorigf.solarstaition.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by chenyp on 2017/7/6.
 * Email : chenyp1994@gmail.com
 */

public abstract class BaseDialogFragment extends DialogFragment implements IContainer {

    protected View root;

    private boolean isFirstLoadData = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutRes(), container, false);
        init(savedInstanceState);
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (root != null && isFirstLoadData && isVisibleToUser) {
            initData();
            isFirstLoadData = false;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isFirstLoadData && getUserVisibleHint()) {
            initData();
            isFirstLoadData = false;
        }
    }

}
