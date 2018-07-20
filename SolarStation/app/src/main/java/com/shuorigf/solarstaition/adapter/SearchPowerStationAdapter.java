package com.shuorigf.solarstaition.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.GlideApp;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/11.
 */

public class SearchPowerStationAdapter extends BaseQuickAdapter<StationListInfo, BaseViewHolder> {

    public SearchPowerStationAdapter(List<StationListInfo> data) {
        super(R.layout.rv_item_search_power_station, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, StationListInfo stationListInfo) {
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
        baseViewHolder.setText(R.id.tv_charging_value, String.format(mContext.getString(R.string.format_k_power), stationListInfo.curChgPow));
        baseViewHolder.setText(R.id.tv_discharge_value, String.format(mContext.getString(R.string.format_k_power), stationListInfo.curDischgPow));
    }
}
