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
    public LogInfo logInfo;

    public static class LogInfo {
        /**
         * 00:00 : 0
         * 02:00 : 20
         * 04:00 : 60
         * 06:00 : 40
         * 08:00 : 80
         * 10:00 : 60
         * 12:00 : 30
         * 14:00 : 40
         * 16:00 : 80
         * 18:00 : 20
         */

        @SerializedName("00:00")
        public float zero;
        @SerializedName("02:00")
        public float two;
        @SerializedName("04:00")
        public float four;
        @SerializedName("06:00")
        public float six;
        @SerializedName("08:00")
        public float eight;
        @SerializedName("10:00")
        public float ten;
        @SerializedName("12:00")
        public float twelve;
        @SerializedName("14:00")
        public float fourteen;
        @SerializedName("16:00")
        public float sixteen;
        @SerializedName("18:00")
        public float eighteen;
    }
}
