package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.home.HomeDataInfo;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class HomepageElectricityAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private HomeDataInfo homeDataInfo;

    public HomepageElectricityAdapter(List<Integer> data, HomeDataInfo homeDataInfo) {
        super(R.layout.rv_item_homepage_electricity, data);
        this.homeDataInfo = homeDataInfo == null ? new HomeDataInfo() : homeDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_electricity, integer);
        String[] values = null;
        switch (integer) {
            case R.string.month_generating_capacity:
                if (homeDataInfo.monthGenerating != null) {
                    values = homeDataInfo.monthGenerating.split(",");
                }
                break;
            case R.string.year_generating_capacity:
                if (homeDataInfo.yearGenerating != null) {
                    values = homeDataInfo.yearGenerating.split(",");
                }
                break;
            case R.string.cumulative_generating_capacity:
                if (homeDataInfo.totalGenerating != null) {
                    values = homeDataInfo.totalGenerating.split(",");
                }
                break;
            case R.string.month_electricity_consumption:
                if (homeDataInfo.monthConsumption != null) {
                    values = homeDataInfo.monthConsumption.split(",");
                }
                break;
            case R.string.year_electricity_consumption:
                if (homeDataInfo.yearConsumption != null) {
                    values = homeDataInfo.yearConsumption.split(",");
                }
                break;
            case R.string.cumulative_electricity_consumption:
                if (homeDataInfo.totalConsumption != null) {
                    values = homeDataInfo.totalConsumption.split(",");
                }
                break;
        }
        if (values != null && values.length > 0) {
            float value_f=Float.parseFloat(values[values.length-1]);
            DecimalFormat df = new DecimalFormat("0.0000");
            value_f=value_f/1000000;
            baseViewHolder.setText(R.id.tv_electricity_value,df.format(value_f)+"");
           /* String value=values[values.length - 1];
            baseViewHolder.setText(R.id.tv_electricity_value,value.substring(0,value.length()-2) );*/
        }

    }
}