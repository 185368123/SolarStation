package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.params.device.DeviceDataLogTableParams;
import com.shuorigf.solarstaition.data.response.device.DeviceDataLogTableListInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/15.
 */

public class DataDetailAdapter extends BaseQuickAdapter<DeviceDataLogTableListInfo.TableBean, BaseViewHolder> {
    String type;

    public DataDetailAdapter(List<DeviceDataLogTableListInfo.TableBean> data, String type) {

        super(R.layout.rv_item_data_detail_content, data);
        this.type = type;

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DeviceDataLogTableListInfo.TableBean deviceDataLogTableListInfo) {
        baseViewHolder.getView(R.id.tv_time).setSelected(true);
        if (type.equals(DeviceDataLogTableParams.TYPE_ALL)){
            baseViewHolder.setText(R.id.tv_time, deviceDataLogTableListInfo.getLog_time());
            baseViewHolder.setText(R.id.tv_current, deviceDataLogTableListInfo.getBattery_chg_cur());
            baseViewHolder.setText(R.id.tv_voltage, deviceDataLogTableListInfo.getBattery_vol());
            baseViewHolder.setText(R.id.tv_power, deviceDataLogTableListInfo.getPanel_chg_pow());
        }else if (type.equals(DeviceDataLogTableParams.TYPE_STORAGE_BATTERY)){
            baseViewHolder.setText(R.id.tv_time, deviceDataLogTableListInfo.getLog_time());
            baseViewHolder.setText(R.id.tv_current, deviceDataLogTableListInfo.getBattery_chg_cur());
            baseViewHolder.setText(R.id.tv_voltage, deviceDataLogTableListInfo.getBattery_vol());
            baseViewHolder.setText(R.id.tv_power, "");
        }else if (type.equals(DeviceDataLogTableParams.TYPE_BATTERY_BOARD)){
            baseViewHolder.setText(R.id.tv_time, deviceDataLogTableListInfo.getLog_time());
            baseViewHolder.setText(R.id.tv_current, deviceDataLogTableListInfo.getPanel_cur());
            baseViewHolder.setText(R.id.tv_voltage, deviceDataLogTableListInfo.getPanel_vol());
            baseViewHolder.setText(R.id.tv_power, deviceDataLogTableListInfo.getPanel_chg_pow());
        }else if (type.equals(DeviceDataLogTableParams.TYPE_LOAD)){
            baseViewHolder.setText(R.id.tv_time, deviceDataLogTableListInfo.getLog_time());
            baseViewHolder.setText(R.id.tv_current, "");
            baseViewHolder.setText(R.id.tv_voltage, deviceDataLogTableListInfo.getLoad_dc_vol());
            baseViewHolder.setText(R.id.tv_power, deviceDataLogTableListInfo.getLoad_dc_pow());
        }


    }
}
