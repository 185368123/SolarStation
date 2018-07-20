package com.shuorigf.solarstaition.ui.fragment.dialog;

import android.os.Bundle;
import android.view.Window;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.base.BaseDialogFragment;

/**
 * Created by clx on 18/2/2.
 */

public class LoadingDialogFragment extends BaseDialogFragment {

    public static LoadingDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 获取LayoutRes
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.dialog_fragment_simple_loading;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState
     */
    @Override
    public void init(Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

    }

}
