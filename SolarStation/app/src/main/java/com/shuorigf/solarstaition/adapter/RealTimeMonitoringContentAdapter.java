package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.device.RealTimeDataInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/19.
 */

public class RealTimeMonitoringContentAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private RealTimeDataInfo realTimeDataInfo;

    public RealTimeMonitoringContentAdapter(List<Integer> data, RealTimeDataInfo realTimeDataInfo) {
        super(R.layout.rv_item_real_time_monitoring_content, data);
        this.realTimeDataInfo = realTimeDataInfo == null ? new RealTimeDataInfo() : realTimeDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        switch (integer) {
            case R.string.electricity_soc:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_electricity_soc),realTimeDataInfo.batterySoc));
                break;
            case R.string.voltage:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_voltage),realTimeDataInfo.batteryVol));
                break;
            case R.string.current:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_current),realTimeDataInfo.batteryChgCur));
                break;
            case R.string.temperature:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_temperature),realTimeDataInfo.batteryTemp));
                break;
            case R.string.charging_state:
                switch (realTimeDataInfo.batteryChgSts) {
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_UNOPENED_CHARGE:
                        baseViewHolder.setText(R.id.tv_content, R.string.unopened_charge);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_START_CHARGING_MODE:
                        baseViewHolder.setText(R.id.tv_content, R.string.charging_mode);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_MPPT_CHARGING_MODE:
                        baseViewHolder.setText(R.id.tv_content, R.string.mppt_charging_mode);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_BALANCED_CHARGING_MODE:
                        baseViewHolder.setText(R.id.tv_content, R.string.balanced_charging_mode);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_UPGRADE_CHARGING_MODE:
                        baseViewHolder.setText(R.id.tv_content, R.string.upgrade_charging_mode);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_FLOAT_CHARGING_MODE:
                        baseViewHolder.setText(R.id.tv_content, R.string.float_charging_mode);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_CHARGE_CURRENT_LIMITING:
                        baseViewHolder.setText(R.id.tv_content, R.string.current_limiting);
                        break;
                }
                break;
            case R.string.minimum_voltage_on_the_same_day:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_voltage),realTimeDataInfo.batteryMinVol));
                break;
            case R.string.maximum_voltage_on_the_same_day:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_voltage),realTimeDataInfo.batteryMaxVol));
                break;
            case R.string.solar_panel_voltage:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_voltage),realTimeDataInfo.panelVol));
                break;
            case R.string.solar_panel_current:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_current),realTimeDataInfo.panelCur));
                break;
            case R.string.charging_power:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_power),realTimeDataInfo.panelChgPow));
                break;
            case R.string.DC_load_voltage:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_voltage),realTimeDataInfo.loadDcVol));
                break;
            case R.string.DC_load_electricity:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_current),realTimeDataInfo.loadDcCur));
                break;
            case R.string.DC_load_power:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_power),realTimeDataInfo.loadDcPow));
                break;
            case R.string.AC_load_voltage:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_voltage),realTimeDataInfo.loadAcVol));
                break;
            case R.string.AC_load_electricity:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_current),realTimeDataInfo.loadAcCur));
                break;
            case R.string.AC_load_power:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_power),realTimeDataInfo.loadAcPow));
                break;
            case R.string.AC_load_frequency:
                baseViewHolder.setText(R.id.tv_content, String.format(mContext.getString(R.string.format_frequency),realTimeDataInfo.loadAcOptFqc));
                break;
        }
    }
}
