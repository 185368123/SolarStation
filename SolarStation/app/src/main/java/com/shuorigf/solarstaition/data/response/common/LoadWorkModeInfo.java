package com.shuorigf.solarstaition.data.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class LoadWorkModeInfo {

    /**
     * 0 : 纯光控、光控开/关负载
     * 1 : 光控开负载,延时1小时后关闭
     * 2 : 光控开负载,延时2小时后关闭
     * 3 : 光控开负载,延时3小时后关闭
     * 4 : 光控开负载,延时4小时后关闭
     * 5 : 光控开负载,延时5小时后关闭
     * 6 : 光控开负载,延时6小时后关闭
     * 7 : 光控开负载,延时7小时后关闭
     * 8 : 光控开负载,延时8小时后关闭
     * 9 : 光控开负载,延时9小时后关闭
     * 10 : 光控开负载,延时10小时后关闭
     * 11 : 光控开负载,延时11小时后关闭
     * 12 : 光控开负载,延时12小时后关闭
     * 13 : 光控开负载,延时13小时后关闭
     * 14 : 光控开负载,延时14小时后关闭
     * 15 : 手动模式
     * 16 : 调试模式
     * 17 : 常开模式
     */

    @SerializedName("0")
    public String mode0;
    @SerializedName("1")
    public String mode1;
    @SerializedName("2")
    public String mode2;
    @SerializedName("3")
    public String mode3;
    @SerializedName("4")
    public String mode4;
    @SerializedName("5")
    public String mode5;
    @SerializedName("6")
    public String mode6;
    @SerializedName("7")
    public String mode7;
    @SerializedName("8")
    public String mode8;
    @SerializedName("9")
    public String mode9;
    @SerializedName("10")
    public String mode10;
    @SerializedName("11")
    public String mode11;
    @SerializedName("12")
    public String mode12;
    @SerializedName("13")
    public String mode13;
    @SerializedName("14")
    public String mode14;
    @SerializedName("15")
    public String mode15;
    @SerializedName("16")
    public String mode16;
    @SerializedName("17")
    public String mode17;
}
