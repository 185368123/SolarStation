package com.shuorigf.solarstaition.data.response.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class DeviceDataLogChartInfo {

    /**
     * max : 900
     * min : 120
     * avg : 450
     * log : {"00:00":"0","02:00":"20","04:00":"60","06:00":"40","08:00":"80","10:00":"60","12:00":"30","14:00":"40","16:00":"80","18:00":"20"}
     */

    @SerializedName("max")
    public String max;
    @SerializedName("min")
    public String min;
    @SerializedName("avg")
    public String avg;
    @SerializedName("log")
    public Object logInfo;

}
