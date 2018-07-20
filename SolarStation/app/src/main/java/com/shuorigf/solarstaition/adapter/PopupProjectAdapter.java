package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.project.ProjectListInfo;

import java.util.List;

/**
 * Created by clx on 2018/3/6.
 */

public class PopupProjectAdapter extends BaseQuickAdapter<ProjectListInfo, BaseViewHolder> {

    public PopupProjectAdapter(List<ProjectListInfo> data) {
        super(R.layout.rv_item_popup_project, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProjectListInfo projectListInfo) {
        baseViewHolder.setText(R.id.tv_title, projectListInfo.projectName);
    }
}
