package com.shuorigf.solarstaition.data.response.project;

import com.google.gson.annotations.SerializedName;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;

import java.util.List;

/**
 * auther: chenlixin on 18/1/22.
 */

public class ProjectListInfo {

    /**
     * project_id : 1
     * project_name : 项目1.0
     * station_count : 13
     * fault_count : 2
     * station_data : [{"station_id":"1","station_name":"Demo电站1","battery_voltage":"500","cur_chg_pow":"300","cur_dischg_pow":"200","status":"1"},{"station_id":"1","station_name":"Demo电站2","battery_voltage":"500","cur_chg_pow":"300","cur_dischg_pow":"200","status":"0"}]
     */

    @SerializedName("project_id")
    public String projectId;
    @SerializedName("project_name")
    public String projectName;
    @SerializedName("station_count")
    public String stationCount;
    @SerializedName("fault_count")
    public String faultCount;
    @SerializedName("station_data")
    public List<StationListInfo> stationListInfo;
}
