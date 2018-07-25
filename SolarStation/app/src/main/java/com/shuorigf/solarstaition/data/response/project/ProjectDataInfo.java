package com.shuorigf.solarstaition.data.response.project;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 * mail: lixin.chen@9leaf.com
 */

public class ProjectDataInfo {

    /**
     * project_name : 项目一
     * guest_name : Evalin
     * company_name : 深圳市离网电站监控有限公司
     * address : 深圳市宝安区前进二路9380号
     * station_count : 88
     * create_time : 2017-08-08 08:08:08
     * fault_count : 28
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

    @SerializedName("project_name")
    public String projectName;
    @SerializedName("guest_name")
    public String guestName;
    @SerializedName("company_name")
    public String companyName;
    @SerializedName("address")
    public String address;
    @SerializedName("station_count")
    public String stationCount;
    @SerializedName("create_time")
    public String createTime;
    @SerializedName("fault_count")
    public String faultCount;
    @SerializedName("electric_reduction")
    public String electricSaving;
    @SerializedName("coal_reduction")
    public String coalSaving;
    @SerializedName("co2_reduction")
    public String co2Emission;
    @SerializedName("so2_reduction")
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
