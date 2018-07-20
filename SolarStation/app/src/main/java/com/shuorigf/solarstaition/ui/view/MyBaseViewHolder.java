package com.shuorigf.solarstaition.ui.view;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.ButterKnife;

/**
 * auther: chenlixin on 18/3/16.
 */

public class MyBaseViewHolder extends BaseViewHolder {

    public MyBaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


}
