package com.shuorigf.solarstaition.data.response.station;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class StationDetailInfo {

    /**
     * name : 电站1
     * project_id : 123
     * install_time : 2017-11-01 08:08:08
     * designer : 奇可艺有限公司
     * photo : http://www.szshuori.com/include/upload/kind/image/20161124/20161124092808_3226.png
     * panel_power : 345
     * panel_type : 2
     * panel_series_count : 43
     * panel_parallel_count : 23
     * panel_count : 78
     * panel_voltage : 54
     * panel_current : 56
     * panel_single_power : 637
     * panel_single_vmp : 34
     * panel_single_voc : 456
     * panel_single_imp : 87
     * panel_single_isc : 234
     * panel_remark : 备注备注备注
     * battery_type : 0
     * battery_single_voltage : 54656
     * battery_single_capacity : 546
     * battery_series_count : 45
     * battery_parallel_count : 23
     * battery_count : 56
     * battery_voltage : 145
     * battery_capacity : 23423
     * battery_remark : 备注备注
     * load_type : 1
     * load_power : 345
     * country_id : 1
     * province_id : 4
     * address : 深圳市南山区深南大道
     * longitude : 114.4547575
     * latitude : 23.6546454
     * altitude : 1214
     * max_annual_temper : 50
     * min_annual_temper : -20
     * geo_remark : 备注备注备注备注
     */

    @SerializedName("name")
    public String name;
    @SerializedName("project_id")
    public String projectId;
    @SerializedName("project_name")
    public String projectName;
    @SerializedName("install_time")
    public String installTime;
    @SerializedName("designer")
    public String designer;
    @SerializedName("photo")
    public String photo;
    @SerializedName("panel_power")
    public String panelPower;
    @SerializedName("panel_type")
    public int panelType;
    @SerializedName("panel_series_count")
    public String panelSeriesCount;
    @SerializedName("panel_parallel_count")
    public String panelParallelCount;
    @SerializedName("panel_count")
    public String panelCount;
    @SerializedName("panel_voltage")
    public String panelVoltage;
    @SerializedName("panel_current")
    public String panelCurrent;
    @SerializedName("panel_single_power")
    public String panelSinglePower;
    @SerializedName("panel_single_vmp")
    public String panelSingleVmp;
    @SerializedName("panel_single_voc")
    public String panelSingleVoc;
    @SerializedName("panel_single_imp")
    public String panelSingleImp;
    @SerializedName("panel_single_isc")
    public String panelSingleIsc;
    @SerializedName("panel_remark")
    public String panelRemark;
    @SerializedName("battery_type")
    public int batteryType;
    @SerializedName("battery_single_voltage")
    public String batterySingleVoltage;
    @SerializedName("battery_single_capacity")
    public String batterySingleCapacity;
    @SerializedName("battery_series_count")
    public String batterySeriesCount;
    @SerializedName("battery_parallel_count")
    public String batteryParallelCount;
    @SerializedName("battery_count")
    public String batteryCount;
    @SerializedName("battery_voltage")
    public String batteryVoltage;
    @SerializedName("battery_capacity")
    public String batteryCapacity;
    @SerializedName("battery_remark")
    public String batteryRemark;
    @SerializedName("load_type")
    public int loadType;
    @SerializedName("load_power")
    public String loadPower;
    @SerializedName("country_id")
    public String countryId;
    @SerializedName("province_id")
    public String provinceId;
    @SerializedName("address")
    public String address;
    @SerializedName("longitude")
    public String longitude;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("altitude")
    public String altitude;
    @SerializedName("max_annual_temper")
    public String maxAnnualTemper;
    @SerializedName("min_annual_temper")
    public String minAnnualTemper;
    @SerializedName("geo_remark")
    public String geoRemark;
}
