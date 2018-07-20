package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.project.ProjectDataInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class ProjectElectricityAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private ProjectDataInfo projectDataInfo;

    public ProjectElectricityAdapter(List<Integer> data, ProjectDataInfo projectDataInfo) {
        super(R.layout.rv_item_project_electricity, data);
        this.projectDataInfo = projectDataInfo == null ? new ProjectDataInfo() : projectDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_electricity, integer);
        String[] values = null;
        switch (integer) {
            case R.string.month_generating_capacity:
                if (projectDataInfo.monthGenerating != null) {
                    values = projectDataInfo.monthGenerating.split(",");
                }
                break;
            case R.string.year_generating_capacity:
                if (projectDataInfo.yearGenerating != null) {
                    values = projectDataInfo.yearGenerating.split(",");
                }
                break;
            case R.string.cumulative_generating_capacity:
                if (projectDataInfo.totalGenerating != null) {
                    values = projectDataInfo.totalGenerating.split(",");
                }
                break;
            case R.string.month_electricity_consumption:
                if (projectDataInfo.monthConsumption != null) {
                    values = projectDataInfo.monthConsumption.split(",");
                }
                break;
            case R.string.year_electricity_consumption:
                if (projectDataInfo.yearConsumption != null) {
                    values = projectDataInfo.yearConsumption.split(",");
                }
                break;
            case R.string.cumulative_electricity_consumption:
                if (projectDataInfo.totalConsumption != null) {
                    values = projectDataInfo.totalConsumption.split(",");
                }
                break;
        }
        if (values != null && values.length > 0) {
            baseViewHolder.setText(R.id.tv_electricity_value, values[values.length - 1]);
        }

    }
}