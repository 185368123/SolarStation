package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.common.BatteryTypeInfo;
import com.shuorigf.solarstaition.data.response.common.LoadTypeInfo;
import com.shuorigf.solarstaition.data.response.common.PanelTypeInfo;
import com.shuorigf.solarstaition.data.response.station.StationDetailInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/7.
 */

public class PowerStationInformationContentAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private StationDetailInfo stationDetailInfo;

    public PowerStationInformationContentAdapter(List<Integer> data, StationDetailInfo stationDetailInfo) {
        super(R.layout.rv_item_simple_content_n, data);
        this.stationDetailInfo = stationDetailInfo == null ? new StationDetailInfo() : stationDetailInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        switch (integer) {
            case R.string.power_station_name:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.name);
                break;
            case R.string.belong_to_project:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.projectName);
                break;
            case R.string.install_date:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.installTime);
                break;
            case R.string.design_vendor:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.designer);
                break;
            case R.string.total_power_of_battery_board:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelPower+" Mwh");
                break;
            case R.string.type_of_battery_board:
                switch (stationDetailInfo.panelType) {
                    case PanelTypeInfo.TYPE_SINGLE:
                        baseViewHolder.setText(R.id.tv_content, R.string.single_crystalline_silicon);
                        break;
                    case PanelTypeInfo.TYPE_MULTI:
                        baseViewHolder.setText(R.id.tv_content, R.string.multi_crystalline_silicon);
                        break;
                    case PanelTypeInfo.TYPE_NON:
                        baseViewHolder.setText(R.id.tv_content, R.string.non_crystalline_silicon);
                        break;
                    case PanelTypeInfo.TYPE_OTHER:
                        baseViewHolder.setText(R.id.tv_content, R.string.other_crystalline_silicon);
                        break;
                }
                break;
            case R.string.the_quantity_of_battery_board_connected_in_series:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelSeriesCount);
                break;
            case R.string.the_quantity_of_battery_board_connected_in_parallel:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelParallelCount);
                break;
            case R.string.power_of_single_battery_board:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelSinglePower+" W");
                break;
            case R.string.total_quantity_of_battery_board:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelCount);
                break;
            case R.string.total_open_circuit_voltage_of_battery_board:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelVoltage+" V");
                break;
            case R.string.total_working_current_of_battery_board:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelCurrent+" A");
                break;
            case R.string.single_battery_board_vmp:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelSingleVmp+" V");
                break;
            case R.string.single_battery_board_voc:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelSingleVoc+" V");
                break;
            case R.string.single_battery_board_imp:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelSingleImp+" A");
                break;
            case R.string.single_battery_board_isc:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelSingleIsc+" A");
                break;
            case R.string.battery_board_remark:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.panelRemark);
                break;
            case R.string.type_of_storage_battery:
                switch (stationDetailInfo.batteryType) {
                    case BatteryTypeInfo.TYPE_CUSTOM:
                        baseViewHolder.setText(R.id.tv_content, R.string.custom);
                        break;
                    case BatteryTypeInfo.TYPE_OPEN:
                        baseViewHolder.setText(R.id.tv_content, R.string.open_mouth);
                        break;
                    case BatteryTypeInfo.TYPE_SEAL:
                        baseViewHolder.setText(R.id.tv_content, R.string.seal);
                        break;
                    case BatteryTypeInfo.TYPE_COLLOID:
                        baseViewHolder.setText(R.id.tv_content, R.string.colloid);
                        break;
                    case BatteryTypeInfo.TYPE_LITHIUM_BATTERY:
                        baseViewHolder.setText(R.id.tv_content, R.string.lithium_battery);
                        break;
                }
                break;
            case R.string.single_battery_voltage:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batterySingleVoltage+" V");
                break;
            case R.string.single_battery_capacity:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batterySingleCapacity+" Ah");
                break;
            case R.string.the_quantity_of_battery_connected_in_series:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batterySeriesCount);
                break;
            case R.string.the_quantity_of_battery_connected_in_parallel:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batteryParallelCount);
                break;
            case R.string.total_quantity_of_battery:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batteryCount);
                break;
            case R.string.total_battery_voltage:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batteryVoltage+" V");
                break;
            case R.string.total_battery_capacity:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batteryCapacity+" Ah");
                break;
            case R.string.storage_battery_remark:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.batteryRemark);
                break;

            case R.string.load_type:
                switch (stationDetailInfo.loadType) {
                    case LoadTypeInfo.TYPE_DC:
                        baseViewHolder.setText(R.id.tv_content, R.string.dc);
                        break;
                    case LoadTypeInfo.TYPE_AC:
                        baseViewHolder.setText(R.id.tv_content, R.string.ac);
                        break;
                    case LoadTypeInfo.TYPE_BOTH:
                        baseViewHolder.setText(R.id.tv_content, R.string.dc_and_ac);
                        break;
                }
                break;
            case R.string.load_power:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.loadPower+" W");
                break;
            case R.string.address:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.address);
                break;
            case R.string.longitude:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.longitude+" °");
                break;
            case R.string.latitude:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.latitude+" °");
                break;
            case R.string.altitude:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.altitude+" m");
                break;
            case R.string.maximum_annual_temperature:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.maxAnnualTemper+" ℃");
                break;
            case R.string.minimum_annual_temperature:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.minAnnualTemper+" ℃");
                break;
            case R.string.geography_remark:
                baseViewHolder.setText(R.id.tv_content, stationDetailInfo.geoRemark);
                break;


        }
    }
}
