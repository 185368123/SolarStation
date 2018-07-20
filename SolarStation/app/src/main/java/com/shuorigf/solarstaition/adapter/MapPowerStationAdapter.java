package com.shuorigf.solarstaition.adapter;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.ui.activity.PowerStationDetailsActivity;

import java.util.List;

/**
 * Created by clx on 2017/10/25.
 */

public class MapPowerStationAdapter extends BaseQuickAdapter<StationListInfo, BaseViewHolder> {

    private int position = -1;

    public MapPowerStationAdapter(List<StationListInfo> data) {
        super(R.layout.rv_item_map_power_station, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, final StationListInfo stationListInfo) {
        TextView name = baseViewHolder.getView(R.id.tv_name);
        View v = baseViewHolder.getView(R.id.v_divide);
        baseViewHolder.setText(R.id.tv_name, stationListInfo.stationName);
        switch (stationListInfo.status) {
            case StationListInfo.STATUS_NORMAL:
                baseViewHolder.setText(R.id.tv_status, R.string.normal);
                break;
            case StationListInfo.STATUS_ABNORMAL:
                baseViewHolder.setText(R.id.tv_status, R.string.abnormal);
                break;
        }
        baseViewHolder.setText(R.id.tv_storage_battery_voltage, String.format(mContext.getString(R.string.format_voltage), stationListInfo.batteryVoltage));
        baseViewHolder.setText(R.id.tv_current_power_generation, stationListInfo.curChgPow);
        baseViewHolder.setText(R.id.tv_current_load_power, stationListInfo.curDischgPow);
        baseViewHolder.setText(R.id.tv_power_station_address, stationListInfo.address);


        if (position == baseViewHolder.getLayoutPosition()) {
            name.setTextColor(ContextCompat.getColor(mContext, R.color.textBlue));
            v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.textBlue));
            baseViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightBlue));
        }else {
            name.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.textGray));
            baseViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.divider));
        }

        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PowerStationDetailsActivity.class);
                intent.putExtra(Constants.STATION_NAME, stationListInfo.stationName);
                intent.putExtra(Constants.STATION_ID, stationListInfo.stationId);
                view.getContext().startActivity(intent);
            }
        });
    }


    public void setPosition(int position) {
        this.position = position;
        this.notifyDataSetChanged();
    }
}
