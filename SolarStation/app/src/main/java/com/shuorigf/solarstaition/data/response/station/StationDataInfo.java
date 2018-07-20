package com.shuorigf.solarstaition.data.response.station;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class StationDataInfo {

    /**
     * station_name : 电站一
     * run_days : 87
     * battery_over_times : 234
     * battery_chg_times : 908
     * fault_count : 3
     * electric_saving : 2000
     * coal_saving : 109
     * co2_emission : 109
     * so2_emission : 109
     * day_generating : 10,11,10,9,10
     * day_consumption : 8,9,8,7,8
     * month_generating : 180,190,180,170,180
     * month_consumption : 120,130,120,110,120
     * year_generating : 3700,3800,3700,3600,3700
     * year_consumption : 2700,2800,2700,2600,2700
     * total_generating : 40000,50000,40000,30000,40000
     * total_consumption : 32000,33000,32000,31000,32000
     */

    @SerializedName("station_name")
    public String stationName;
    @SerializedName("run_days")
    public String runDays;
    @SerializedName("battery_over_times")
    public String batteryOverTimes;
    @SerializedName("battery_chg_times")
    public String batteryChgTimes;
    @SerializedName("fault_count")
    public String faultCount;
    @SerializedName("electric_saving")
    public String electricSaving;
    @SerializedName("coal_saving")
    public String coalSaving;
    @SerializedName("co2_emission")
    public String co2Emission;
    @SerializedName("so2_emission")
    public String so2Emission;
    @SerializedName("day_generating")
    public String dayGenerating;
    @SerializedName("day_consumption")
    public String dayConsumption;
    @SerializedName("month_generating")
    public String monthGenerating;
    @SerializedName("month_consumption")
    public String monthConsumption;
    @SerializedName("year_generating")
    public String yearGenerating;
    @SerializedName("year_consumption")
    public String yearConsumption;
    @SerializedName("total_generating")
    public String totalGenerating;
    @SerializedName("total_consumption")
    public String totalConsumption;
}
