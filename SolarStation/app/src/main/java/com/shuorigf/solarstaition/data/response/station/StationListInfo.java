package com.shuorigf.solarstaition.data.response.station;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class StationListInfo {
    public static final int STATUS_ABNORMAL= 0;
    public static final int STATUS_NORMAL = 1;

    /**
     * station_id : 1
     * station_name : Demo电站1
     * station_img
     * battery_voltage : 100
     * cur_chg_pow : 0.5
     * cur_dischg_pow : 0.6
     * address : 中国深圳某某区1
     * status : 1
     * longitude : 113.845615
     * latitude : 22.61293
     */

    @SerializedName("station_id")
    public String stationId;
    @SerializedName("station_name")
    public String stationName;
    @SerializedName("station_img")
    public String stationImg;
    @SerializedName("battery_voltage")
    public String batteryVoltage;
    @SerializedName("cur_chg_pow")
    public String curChgPow;
    @SerializedName("cur_dischg_pow")
    public String curDischgPow;
    @SerializedName("address")
    public String address;
    @SerializedName("status")
    public int status;
    @SerializedName("longitude")
    public double longitude;
    @SerializedName("latitude")
    public double latitude;
}
