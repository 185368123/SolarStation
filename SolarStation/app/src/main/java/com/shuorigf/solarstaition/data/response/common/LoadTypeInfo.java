package com.shuorigf.solarstaition.data.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class LoadTypeInfo {
    public static final int TYPE_DC = 0;
    public static final int TYPE_AC = 1;
    public static final int TYPE_BOTH = 2;

    /**
     * 0 : 直流
     * 1 : 交流
     * 2 : 直流+交流
     */

    @SerializedName("0")
    public String dc;
    @SerializedName("1")
    public String ac;
    @SerializedName("2")
    public String both;
}

