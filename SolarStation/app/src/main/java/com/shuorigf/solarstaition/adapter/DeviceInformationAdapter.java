package com.shuorigf.solarstaition.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class DeviceInformationAdapter extends BaseQuickAdapter<DeviceListInfo, BaseViewHolder>{

    public DeviceInformationAdapter(List<DeviceListInfo> data) {
        super(R.layout.rv_item_device_information_content, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DeviceListInfo deviceListInfo) {
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
        baseViewHolder.setText(R.id.tv_type, String.format(mContext.getString(R.string.format_product_type),deviceListInfo.type));
        baseViewHolder.setText(R.id.tv_mode, String.format(mContext.getString(R.string.format_product_model),deviceListInfo.model));
        baseViewHolder.setText(R.id.tv_hardware, String.format(mContext.getString(R.string.format_hardware_version),deviceListInfo.hwVersion));
        baseViewHolder.setText(R.id.tv_software, String.format(mContext.getString(R.string.format_software_version),deviceListInfo.swVersion));
        baseViewHolder.setText(R.id.tv_device_id, String.format(mContext.getString(R.string.format_device_id),deviceListInfo.serialNo));
        baseViewHolder.addOnClickListener(R.id.iv_swipe_edit);
        baseViewHolder.addOnClickListener(R.id.iv_swipe_delete);
    }
    
}
