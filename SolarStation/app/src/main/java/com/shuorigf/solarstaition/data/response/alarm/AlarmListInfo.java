package com.shuorigf.solarstaition.data.response.alarm;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class AlarmListInfo {

    /**
     * alarm_time : 2018-01-01 00:00:00
     * alarm_type : 错误
     * device_type : 电池板
     * serial_no : HDJEI6546421
     * device_address : ADDRI6546421
     * alarm_desc : 描述详细内容
     * marker : 报警特殊标注
     * recovery_time : 2018-01-02 00:00:00
     */

    @SerializedName("alarm_time")
    public String alarmTime;
    @SerializedName("alarm_type")
    public String alarmType;
    @SerializedName("device_type")
    public String deviceType;
    @SerializedName("serial_no")
    public String serialNo;
    @SerializedName("device_address")
    public String deviceAddress;
    @SerializedName("alarm_desc")
    public String alarmDesc;
    @SerializedName("marker")
    public String marker;
    @SerializedName("recovery_time")
    public String recoveryTime;
}
