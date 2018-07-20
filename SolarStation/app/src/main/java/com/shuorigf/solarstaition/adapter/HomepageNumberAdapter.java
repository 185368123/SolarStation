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

public class HomepageNumberAdapter extends BaseQuickAdapter<IconText, BaseViewHolder> {

    private HomeDataInfo homeDataInfo;

    public HomepageNumberAdapter(List<IconText> data, HomeDataInfo homeDataInfo) {
        super(R.layout.rv_item_homepage_number, data);
        this.homeDataInfo = homeDataInfo == null ? new HomeDataInfo() : homeDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, IconText iconText) {
        TextView number = baseViewHolder.getView(R.id.tv_number);
        number.setText(iconText.title);
        number.setCompoundDrawablesWithIntrinsicBounds(iconText.icon, 0, 0, 0);
        switch (iconText.title) {
            case R.string.project_number:
                baseViewHolder.setText(R.id.tv_number_value, homeDataInfo.projectCount);
                break;
            case R.string.power_station_number:
                baseViewHolder.setText(R.id.tv_number_value, homeDataInfo.stationCount);
                break;
        }
    }
}
