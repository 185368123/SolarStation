package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.device.ReadSettingInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/15.
 */

public class ParameterSettingContentAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private ReadSettingInfo readSettingInfo;

    public ParameterSettingContentAdapter(List<Integer> data, ReadSettingInfo readSettingInfo) {
        super(R.layout.rv_item_simple_content_m, data);
        this.readSettingInfo = readSettingInfo == null ? new ReadSettingInfo() : readSettingInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        switch (integer) {
            case R.string.nominal_capacity:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.capacity);
                break;
            case R.string.overpressure_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.overVol);
                break;
            case R.string.charge_limit_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.limitedChargeVol);
                break;
            case R.string.balance_charge_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.balanceChargeVol);
                break;
            case R.string.lifting_charge_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.promoteChargeVol);
                break;
            case R.string.floating_charge_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.overVol);
                break;
            case R.string.lifting_charge_return_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.limitedChargeVol);
                break;
            case R.string.over_discharge_return_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.balanceChargeVol);
                break;
            case R.string.undervoltage_warning_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.promoteChargeVol);
                break;
            case R.string.over_discharge_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.overVol);
                break;
            case R.string.discharge_limit_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.limitedChargeVol);
                break;
            case R.string.over_discharge_delay_time:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.balanceChargeVol);
                break;
            case R.string.balance_charge_time:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.promoteChargeVol);
                break;
            case R.string.lifting_charge_time:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.overVol);
                break;
            case R.string.balance_charge_interval:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.limitedChargeVol);
                break;
            case R.string.temperature_compensation_coefficient:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.balanceChargeVol);
                break;
            case R.string.load_work_mode:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.overVol);
                break;
            case R.string.light_control_delay_time:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.limitedChargeVol);
                break;
            case R.string.light_control_voltage:
                baseViewHolder.setText(R.id.tv_content, readSettingInfo.balanceChargeVol);
                break;
        }
    }
}
