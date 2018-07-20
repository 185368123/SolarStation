package com.shuorigf.solarstaition.data.params.alarm;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class AlarmListParams {
    public static final String STATION_ID = "station_id";
    public static final String DATE = "date";
    public static final String ALARM_TYPE = "alarm_type";
    public static final String LABEL = "label";
    public static final String DEVICE_ID = "device_id";

    @SerializedName("station_id")
    public String stationId;
    @SerializedName("date")
    public String date;
    @SerializedName("alarm_type")
    public String alarmType;
    @SerializedName("label")
    public String label;
    @SerializedName("device_id")
    public String deviceId;

    public AlarmListParams() {

    }

    /**
     *
     * @param stationId 电站id Y
     * @param date 时间 N
     * @param alarmType 报警类型 N
     * @param label 标注 N
     * @param deviceId 设备id N
     */
    public AlarmListParams(String stationId, String date, String alarmType, String label, String deviceId) {
        this.stationId = stationId;
        this.date = date;
        this.alarmType = alarmType;
        this.label = label;
        this.deviceId = deviceId;
    }
}
