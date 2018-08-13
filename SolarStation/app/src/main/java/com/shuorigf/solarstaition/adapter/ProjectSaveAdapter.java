package com.shuorigf.solarstaition.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.IconText;
import com.shuorigf.solarstaition.data.response.project.ProjectDataInfo;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class ProjectSaveAdapter extends BaseQuickAdapter<IconText, BaseViewHolder> {

    private ProjectDataInfo projectDataInfo;
    DecimalFormat df = new DecimalFormat("0.0000");
    public ProjectSaveAdapter(List<IconText> data, ProjectDataInfo projectDataInfo) {
        super(R.layout.rv_item_project_save, data);
        this.projectDataInfo = projectDataInfo == null ? new ProjectDataInfo() : projectDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, IconText iconText) {
        TextView save = baseViewHolder.getView(R.id.tv_save);
        save.setText(iconText.title);
        save.setCompoundDrawablesWithIntrinsicBounds(iconText.icon, 0, 0, 0);
        switch (iconText.title) {
            case R.string.save_electricity:
                float value_f=Float.parseFloat(projectDataInfo.electricSaving);
                value_f=value_f/1000000;
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_electricity),df.format(value_f)+"" ));
                break;
            case R.string.save_standard_coal:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), projectDataInfo.coalSaving));
                break;
            case R.string.so2_emission_reduction:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), projectDataInfo.so2Emission));
                break;
            case R.string.co2_emission_reduction:
                baseViewHolder.setText(R.id.tv_save_value, String.format(mContext.getString(R.string.format_ton), projectDataInfo.co2Emission));
                break;
        }
    }
}
