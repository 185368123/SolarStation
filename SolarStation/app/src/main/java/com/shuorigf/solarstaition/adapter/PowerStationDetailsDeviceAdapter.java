package com.shuorigf.solarstaition.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.ui.activity.DeviceManageActivity;

import java.util.List;

/**
 * Created by clx on 2017/10/12.
 */

public class PowerStationDetailsDeviceAdapter extends BaseQuickAdapter<DeviceListInfo, BaseViewHolder> {
String mStationId;
    public PowerStationDetailsDeviceAdapter(List<DeviceListInfo> data,String mStationId) {
        super(R.layout.rv_item_power_station_details_device, data);
        this.mStationId=mStationId;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, final DeviceListInfo deviceListInfo) {
        baseViewHolder.setText(R.id.tv_name, deviceListInfo.type);
        TextView status = baseViewHolder.getView(R.id.tv_status);
        switch (deviceListInfo.status) {
            case DeviceListInfo.STATUS_NORMAL:
                status.setText(R.string.normal);
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rectangle_green_8dp, 0, 0, 0);
                break;
            case DeviceListInfo.STATUS_ABNORMAL:
                status.setText(R.string.abnormal);
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rectangle_red_8dp, 0, 0, 0);
                break;
        }
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DeviceManageActivity.class);
                intent.putExtra(Constants.DEVICE_NAME, deviceListInfo.type);
                intent.putExtra(Constants.DEVICE_ID, deviceListInfo.id);
                intent.putExtra(Constants.STATION_ID, mStationId);
                view.getContext().startActivity(intent);
            }
        });
    }
}
