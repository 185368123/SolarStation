package com.shuorigf.solarstaition.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.IconText;
import com.shuorigf.solarstaition.data.response.station.StationDataInfo;

import java.util.List;

/**
 * Created by clx on 2018/3/1.
 */

public class PowerStationSaveAdapter extends BaseQuickAdapter<IconText, BaseViewHolder> {

    private StationDataInfo stationDataInfo;

    public PowerStationSaveAdapter(List<IconText> data, StationDataInfo stationDataInfo) {
        super(R.layout.rv_item_power_station_save, data);
        this.stationDataInfo = stationDataInfo == null ? new StationDataInfo() : stationDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, IconText iconText) {
        TextView save = baseViewHolder.getView(R.id.tv_save);
        save.setText(iconText.title);
        save.setCompoundDrawablesWithIntrinsicBounds(iconText.icon, 0, 0, 0);
        switch (iconText.title) {
            case R.string.save_electricity:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_electricity), stationDataInfo.electricSaving));
                break;
            case R.string.save_standard_coal:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), stationDataInfo.coalSaving));
                break;
            case R.string.so2_emission_reduction:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), stationDataInfo.so2Emission));
                break;
            case R.string.co2_emission_reduction:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), stationDataInfo.co2Emission));
                break;
        }
    }
}
