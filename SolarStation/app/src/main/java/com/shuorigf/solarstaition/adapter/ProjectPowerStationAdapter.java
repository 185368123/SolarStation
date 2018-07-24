package com.shuorigf.solarstaition.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.GlideApp;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.ui.activity.PowerStationDetailsActivity;

import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class ProjectPowerStationAdapter extends BaseQuickAdapter<StationListInfo, BaseViewHolder> {
    String project_id;

    public ProjectPowerStationAdapter(List<StationListInfo> data,String project_id) {
        super(R.layout.rv_item_project_power_station, data);
        this.project_id=project_id;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final StationListInfo stationListInfo) {
        ImageView image = baseViewHolder.getView(R.id.iv_icon);
        GlideApp.with(image.getContext())
                .load(stationListInfo.stationImg)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(image);
        baseViewHolder.setText(R.id.tv_name, stationListInfo.stationName);
        switch (stationListInfo.status) {
            case StationListInfo.STATUS_NORMAL:
                baseViewHolder.setText(R.id.tv_status, R.string.normal);
                break;
            case StationListInfo.STATUS_ABNORMAL:
                baseViewHolder.setText(R.id.tv_status, R.string.abnormal);
                break;
        }
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PowerStationDetailsActivity.class);
                intent.putExtra(Constants.STATION_NAME, stationListInfo.stationName);
                intent.putExtra(Constants.STATION_ID, stationListInfo.stationId);
                intent.putExtra(Constants.PROJECT_ID, project_id);
                view.getContext().startActivity(intent);
            }
        });
    }
}
