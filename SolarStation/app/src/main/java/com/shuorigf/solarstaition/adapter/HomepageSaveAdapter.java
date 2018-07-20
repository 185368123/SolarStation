package com.shuorigf.solarstaition.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.IconText;
import com.shuorigf.solarstaition.data.response.home.HomeDataInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class HomepageSaveAdapter extends BaseQuickAdapter<IconText, BaseViewHolder> {

    private HomeDataInfo homeDataInfo;

    public HomepageSaveAdapter(List<IconText> data, HomeDataInfo homeDataInfo) {
        super(R.layout.rv_item_homepage_save, data);
        this.homeDataInfo = homeDataInfo == null ? new HomeDataInfo() : homeDataInfo;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, IconText iconText) {
        TextView save = baseViewHolder.getView(R.id.tv_save);
        save.setText(iconText.title);
        save.setCompoundDrawablesWithIntrinsicBounds(iconText.icon, 0, 0, 0);
        switch (iconText.title) {
            case R.string.save_electricity:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_electricity), homeDataInfo.electricSaving));
                break;
            case R.string.save_standard_coal:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), homeDataInfo.coalSaving));
                break;
            case R.string.so2_emission_reduction:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), homeDataInfo.so2Emission));
                break;
            case R.string.co2_emission_reduction:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), homeDataInfo.co2Emission));
                break;
        }
    }
}
