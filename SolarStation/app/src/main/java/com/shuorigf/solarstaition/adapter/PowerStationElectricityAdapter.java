package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.station.StationDataInfo;

import java.util.List;

/**
 * Created by clx on 2018/3/1.
 */

public class PowerStationElectricityAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private StationDataInfo stationDataInfo;

    public PowerStationElectricityAdapter(List<Integer> data, StationDataInfo stationDataInfo) {
        super(R.layout.rv_item_power_station_electricity, data);
        this.stationDataInfo = stationDataInfo == null ? new StationDataInfo() : stationDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_electricity, integer);
        String[] values = null;
        switch (integer) {
            case R.string.month_generating_capacity:
                if (stationDataInfo.monthGenerating != null) {
                    values = stationDataInfo.monthGenerating.split(",");
                }
                break;
            case R.string.year_generating_capacity:
                if (stationDataInfo.yearGenerating != null) {
                    values = stationDataInfo.yearGenerating.split(",");
                }
                break;
            case R.string.cumulative_generating_capacity:
                if (stationDataInfo.totalGenerating != null) {
                    values = stationDataInfo.totalGenerating.split(",");
                }
                break;
            case R.string.month_electricity_consumption:
                if (stationDataInfo.monthConsumption != null) {
                    values = stationDataInfo.monthConsumption.split(",");
                }
                break;
            case R.string.year_electricity_consumption:
                if (stationDataInfo.yearConsumption != null) {
                    values = stationDataInfo.yearConsumption.split(",");
                }
                break;
            case R.string.cumulative_electricity_consumption:
                if (stationDataInfo.totalConsumption != null) {
                    values = stationDataInfo.totalConsumption.split(",");
                }
                break;
        }
        if (values != null && values.length > 0) {
            baseViewHolder.setText(R.id.tv_electricity_value, values[values.length - 1]);
        }

    }


}