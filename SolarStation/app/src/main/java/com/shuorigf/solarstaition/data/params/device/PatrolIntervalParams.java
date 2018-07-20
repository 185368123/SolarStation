package com.shuorigf.solarstaition.data.params.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class PatrolIntervalParams {

    @SerializedName("station_id")
    public String stationId;
    @SerializedName("interval")
    public String interval;

    /**
     *
     * @param stationId 电站id
     * @param interval 时间间隔(分钟)
     */
    public PatrolIntervalParams(String stationId, String interval) {
        this.stationId = stationId;
        this.interval = interval;
    }
}
