package com.shuorigf.solarstaition.data.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class PanelTypeInfo {
    public static final int TYPE_SINGLE = 0;
    public static final int TYPE_MULTI = 1;
    public static final int TYPE_NON = 2;
    public static final int TYPE_OTHER = 3;

    /**
     * 0 : 单晶硅
     * 1 : 多晶硅
     * 2 : 非晶硅
     * 3 : 其他
     */

    @SerializedName("0")
    public String single;
    @SerializedName("1")
    public String multi;
    @SerializedName("2")
    public String non;
    @SerializedName("3")
    public String other;
}
