package com.shuorigf.solarstaition.data.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class BatteryTypeInfo {
    public static final int TYPE_CUSTOM = 0;
    public static final int TYPE_OPEN = 1;
    public static final int TYPE_SEAL = 2;
    public static final int TYPE_COLLOID = 3;
    public static final int TYPE_LITHIUM_BATTERY = 4;

    /**
     * 0 : 自定义
     * 1 : 开口
     * 2 : 密封
     * 3 : 胶体
     * 4 : 锂电池
     */

    @SerializedName("0")
    public String custom;
    @SerializedName("1")
    public String open;
    @SerializedName("2")
    public String seal;
    @SerializedName("3")
    public String colloid;
    @SerializedName("4")
    public String lithiumBattery;
}
