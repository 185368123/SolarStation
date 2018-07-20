package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;

import java.util.List;

/**
 * Created by clx on 18/1/26.
 */

public class SimpleMenuAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public SimpleMenuAdapter(List<Integer> data) {
        super(R.layout.rv_item_simple_menu, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
    }
}
